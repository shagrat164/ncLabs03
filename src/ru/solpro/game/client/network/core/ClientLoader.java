/*
 * @(#)ClientLoader.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core;

import ru.solpro.game.client.network.core.packet.Packet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientLoader {
    private static Socket socket;

    /**
     * Подключение к серверу.
     */
    public static void connect(String host, int port) {
        if ((socket == null) || (socket.isClosed()) || (!socket.isBound())) {
            try {
                socket = new Socket(host, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ClientHandler(socket).start();
        }
    }

    /**
     * Отключение от сервера.
     */
    public static void disconnect() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (SocketException e) {
            /*NOP*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacket(Packet packet) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeShort(packet.getId());
            packet.write(dataOutputStream);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
