/*
 * @(#)ClientHandler.java 1.0 08.01.2017
 */

package ru.solpro.game.server.core;

import ru.solpro.game.server.core.packet.Packet;
import ru.solpro.game.server.core.packet.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientHandler extends Thread {
    private final Socket client;

    ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            // чтение и обработка данных от клиента
            if (!readData()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Чтение данных от клиента.
     * Чтение пакетов.
     * @return
     */
    private boolean readData() {
        try {
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            if (dataInputStream.available() <= 0) {
                return false;
            }
            short id = dataInputStream.readShort();
            // чтение пакета от клиента
            Packet packet = PacketManager.getPacket(id);
            if (packet != null) {
                packet.setSocket(client);
                packet.read(dataInputStream);
                packet.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void invalidate() {
        ServerLoader.invalidateSocket(client);
    }
}
