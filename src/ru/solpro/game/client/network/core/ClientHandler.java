/*
 * @(#)ClientHandler.java 1.0 10.01.2017
 */

package ru.solpro.game.client.network.core;

import ru.solpro.game.client.network.core.packet.Packet;
import ru.solpro.game.client.network.core.packet.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientHandler extends Thread {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            // чтение и обработка данных от сервера
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                if (dataInputStream.available() <= 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                short id = dataInputStream.readShort();
                // чтение пакета от сервера
                Packet packet = PacketManager.getPacket(id);
                if (packet != null) {
                    packet.read(dataInputStream);
                    packet.handle();
                }
            } catch (SocketException e) {
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
