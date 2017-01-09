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
    private String nickname = "no nickname";

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void run() {
        while (true) {
            //TODO: чтение и обработка данных от клиента
            readData();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readData() {
        try {
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            if (dataInputStream.available() <= 0) {
                return;
            }
            short id = dataInputStream.readShort();
            //TODO: чтение пакета от клиента
            Packet packet = PacketManager.getPacket(id);
            if (packet != null) {
                packet.setSocket(client);
                packet.read(dataInputStream);
                packet.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void invalidate() {
        ServerLoader.invalidate(client);
    }
}
