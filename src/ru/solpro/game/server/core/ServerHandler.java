/*
 * @(#)ServerHandler.java 1.0 10.01.2017
 */

package ru.solpro.game.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ServerHandler extends Thread {
    private ServerSocket serverSocket;

    ServerHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.setDaemon(true);
                handler.start();
                ServerLoader.getHandlers().put(client, handler);
            } catch (SocketException e) {
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
}
