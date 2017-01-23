/*
 * @(#)ServerLoader.java 1.0 05.01.2017
 */

package ru.solpro.game.server.core;

import ru.solpro.game.server.controller.RootLayoutController;
import ru.solpro.game.server.core.datasrv.LogServer;
import ru.solpro.game.server.core.packet.Packet;
import ru.solpro.game.server.model.Battle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
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

    private static int port = 65000;

    private static RootLayoutController rootLayoutController;

    private static ServerSocket serverSocket;

    // клиенты
    private static Map<Socket, Client> handlers = new HashMap<>();

    // бои
    private static Map<Integer, Battle> battles = new HashMap<>();

    private static void start() {
        if ((serverSocket == null) || (serverSocket.isClosed()) || (!serverSocket.isBound())) {
            try {
                serverSocket = new ServerSocket(port);
                rootLayoutController.getButtonStopServer().setDisable(false);
                rootLayoutController.getButtonStartServer().setDisable(true);
                rootLayoutController.getButtonViewSetting().setDisable(true);
                LogServer.info(String.format("Сервер запущен. %s:%d",
                        InetAddress.getLocalHost().getHostAddress(),
                        serverSocket.getLocalPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Поток слушает порт.
     * При подключении клаента на порт, создаёт клиента.
     * После спит 10мс.
     * @see Client
     */
    @Override
    public void run() {
        start();
        while (true) {
            try {
                Socket client = serverSocket.accept();
                Client handler = new Client(client);
                handler.setDaemon(true);
                handler.start();
                handlers.put(client, handler);
            } catch (SocketException e) {
                //завершение выполнения при закрытии сокета
                break;
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

    public static void stop() {
        if (serverSocket == null) {
            return;
        }
        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();

                rootLayoutController.getButtonStopServer().setDisable(true);
                rootLayoutController.getButtonStartServer().setDisable(false);
                rootLayoutController.getButtonViewSetting().setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handlers.clear();
        battles.clear();
        LogServer.info("Сервер остановлен.");
    }

    /**
     * Отправить данные клиенту.
     * @param receiver получатель.
     * @param packet   пакет.
     */
    public static synchronized void sendPacket(Socket receiver, Packet packet) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(receiver.getOutputStream());
            dataOutputStream.writeShort(packet.getId());
            packet.write(dataOutputStream);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRootLayoutController(RootLayoutController rootLayoutController) {
        ServerLoader.rootLayoutController = rootLayoutController;
    }

    public static RootLayoutController getRootLayoutController() {
        return rootLayoutController;
    }

    public static Map<Socket, Client> getHandlers() {
        return handlers;
    }

    public static Client getHandler(Socket client) {
        return handlers.get(client);
    }

    public static Client getHandler(String nickname) {
        for (Client client : handlers.values()) {
            if (client.getNickname().equals(nickname)) {
                return client;
            }
        }
        return null;
    }

    /**
     * Удаление клиента по определённому сокету.
     * Из списка слушателей и игроков.
     * @param client сокет клиента
     */
    public static void invalidateSocket(Socket client) {
        handlers.remove(client);
    }

    public static Map<Integer, Battle> getBattles() {
        return battles;
    }

    public static Battle getBattle(Integer id) {
        return battles.get(id);
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ServerLoader.port = port;
    }

    public static void invalidateBattle(Integer id) {
        battles.remove(id);
    }
}
