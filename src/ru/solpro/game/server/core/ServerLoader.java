/*
 * @(#)Server.java 1.0 05.01.2017
 */
package ru.solpro.game.server.core;

import java.io.IOException;
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
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();

    @Override
    public void run() {
        start();
        handle();
    }

    private static void start() {
        try {
            serverSocket = new ServerSocket(65000);
            System.out.println("ServerStart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                handlers.put(client, handler);
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
        try {
            serverSocket.close();
            System.out.println("ServerStop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientHandler getHandler(Socket client) {
        return handlers.get(client);
    }

    public static void invalidate(Socket client) {
        handlers.remove(client);
    }
}
