/*
 * @(#)Client.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core;

import ru.solpro.game.client.network.core.packet.AuthenticationPacket;
import ru.solpro.game.client.network.core.packet.LogoutPacket;
import ru.solpro.game.client.network.core.packet.MessagePacket;
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
    private static boolean sendNickname = false;

    public static void connect() {
        try {
            socket = new Socket("localhost", 65000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connect()");
    }

    public static void handle() {
        new ClientHandler(socket).start();
        System.out.println("handle()");
    }

    public static void readChat(String nickname, String message) {
        if (!sendNickname) {
            sendNickname = true;
            sendPacket(new AuthenticationPacket("userName02"));
            return;
        }
        sendPacket(new MessagePacket(nickname, message));
    }

    public static void disconnect() {
        sendPacket(new LogoutPacket("userName02"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        try {
            socket.close();
        } catch (SocketException e) {
            /*NOP*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("disconnect()");
    }

    private static void sendPacket(Packet packet) {
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
