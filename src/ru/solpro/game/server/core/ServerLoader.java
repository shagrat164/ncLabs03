/*
 * @(#)ServerLoader.java 1.0 05.01.2017
 */

package ru.solpro.game.server.core;

import ru.solpro.game.server.controller.RootLayoutController;
import ru.solpro.game.server.core.datasrv.LogServer;
import ru.solpro.game.server.core.packet.Packet;
import ru.solpro.game.server.model.Battle;
import ru.solpro.game.server.model.Player;

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

    private static int port = 65000;

    private static RootLayoutController rootLayoutController;

    private static ServerSocket serverSocket;
    // клиенты
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();
    // пользователи (игроки)
    private static Map<Socket, Player> players = new HashMap<>();
    // бои
    private static Map<Integer, Battle> battles = new HashMap<>();

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ServerLoader.port = port;
    }

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
                serverSocket = new ServerSocket(port);
                rootLayoutController.getButtonStopServer().setDisable(false);
                rootLayoutController.getButtonStartServer().setDisable(true);
                rootLayoutController.getButtonViewSetting().setDisable(true);
                LogServer.info(String.format("Сервер запущен. Порт %d", serverSocket.getLocalPort()));
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
        if (serverSocket == null) {
            return;
        }
        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
                rootLayoutController.getButtonStopServer().setDisable(true);
                rootLayoutController.getButtonStartServer().setDisable(false);
                rootLayoutController.getButtonViewSetting().setDisable(false);
                LogServer.info("Сервер остановлен.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setRootLayoutController(RootLayoutController rootLayoutController) {
        ServerLoader.rootLayoutController = rootLayoutController;
    }

    public static RootLayoutController getRootLayoutController() {
        return rootLayoutController;
    }

    public static Map<Socket, ClientHandler> getHandlers() {
        return handlers;
    }

    public static ClientHandler getHandler(Socket client) {
        return handlers.get(client);
    }

    public static Map<Socket, Player> getPlayers() {
        return players;
    }

    public static Player getPlayer(Socket client) {
        return players.get(client);
    }

    /**
     * Удаление клиента по определённому сокету.
     * Из списка слушателей и игроков.
     * @param client сокет клиента
     */
    public static void invalidateSocket(Socket client) {
        players.remove(client);
        handlers.remove(client);
    }

    public static Map<Integer, Battle> getBattles() {
        return battles;
    }

    public static Battle getBattle(Integer id) {
        return battles.get(id);
    }

    public static void invalidateBattle(Integer id) {
        battles.remove(id);
    }
}
