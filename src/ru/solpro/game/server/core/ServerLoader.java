/*
 * @(#)Server.java 1.0 05.01.2017
 */
package ru.solpro.game.server.core;

import ru.solpro.game.server.core.packet.Packet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ServerLoader implements Runnable {

    private static ServerSocket serverSocket;
    private static ServerHandler handler;
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();

    @Override
    public void run() {
        start();
        handle();
        System.out.println("ServerLoader.run()");
    }

    private static void start() {
        try {
            serverSocket = new ServerSocket(65000);
            System.out.println("ServerStart " + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        handler = new ServerHandler(serverSocket);
        handler.start();
        readChat();
    }

    private static void readChat() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String str = reader.readLine();
                if ("/end".equals(str)) {
                    stop();
                    break;
                } else {
                    System.out.println("Неизвесная команда.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
        try {
            serverSocket.close();
            System.out.println("ServerStop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Socket, ClientHandler> getHandlers() {
        return handlers;
    }

    public static ClientHandler getHandler(Socket client) {
        return handlers.get(client);
    }

    public static void invalidate(Socket client) {
        handlers.remove(client);
    }
}
