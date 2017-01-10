/*
 * @(#)ServerLoader.java 1.0 05.01.2017
 */
package ru.solpro.game.server.core;

import ru.solpro.game.server.core.packet.Packet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ServerLoader implements Runnable {

    private static ServerSocket serverSocket;
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();

    @Override
    public void run() {
        start();
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.setDaemon(true);
                handler.start();
                ServerLoader.getHandlers().put(client, handler);
            } catch (SocketException e) {
                //завершение выполнения при закрытии сокета
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void start() {
        if ((serverSocket == null) || (serverSocket.isClosed()) || (!serverSocket.isBound())) {
            try {
                serverSocket = new ServerSocket(65000);
                System.out.println("ServerStart " + serverSocket.getInetAddress().getCanonicalHostName() + ":" + serverSocket.getLocalPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Отправить данные клиенту.
     * @param receiver получатель.
     * @param packet   пакет.
     */
    public static void sendPacket(Socket receiver, Packet packet) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(receiver.getOutputStream());
            dataOutputStream.writeShort(packet.getId());
            packet.write(dataOutputStream);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
                System.out.println("ServerStop");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<Socket, ClientHandler> getHandlers() {
        return handlers;
    }

    public static ClientHandler getHandler(Socket client) {
        return handlers.get(client);
    }

    static void invalidate(Socket client) {
        handlers.remove(client);
    }
}
