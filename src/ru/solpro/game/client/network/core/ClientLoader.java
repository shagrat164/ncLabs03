/*
 * @(#)Client.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core;

import ru.solpro.game.client.network.core.packet.AuthenticationPacket;
import ru.solpro.game.client.network.core.packet.LogoutPacket;
import ru.solpro.game.client.network.core.packet.Packet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientLoader {
    private static Socket socket;

    public static void connect() {
        try {
            socket = new Socket("localhost", 65000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handle() {
        sendPacket(new AuthenticationPacket("nick1"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendPacket(new LogoutPacket("nick1"));
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

    public static void recivePacket() {

    }
}
