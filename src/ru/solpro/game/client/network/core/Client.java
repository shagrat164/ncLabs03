/*
 * @(#)Client.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core;

import ru.solpro.game.client.network.core.packet.Packet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Client {
    private static Socket socket;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handle() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(127);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            socket.close();
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
