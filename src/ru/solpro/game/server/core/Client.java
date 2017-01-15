/*
 * @(#)ClientHandler.java 1.0 08.01.2017
 */

package ru.solpro.game.server.core;

import ru.solpro.game.server.core.packet.Packet;
import ru.solpro.game.server.core.packet.PacketManager;
import ru.solpro.game.server.model.StatusPlayer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Client extends Thread {

    private static int count = 0;
    private int userId;
    private String nickname = "";
    private StatusPlayer statusPlayer;
    private Socket socket;

    Client(Socket socket) {
        count++;
        this.userId = count;
        this.socket = socket;
        this.statusPlayer = StatusPlayer.FREE;
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
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            if (dataInputStream.available() <= 0) {
                return false;
            }
            short id = dataInputStream.readShort();
            // чтение пакета от клиента
            Packet packet = PacketManager.getPacket(id);
            if (packet != null) {
                packet.setSocket(socket);
                packet.read(dataInputStream);
                packet.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public StatusPlayer getStatusPlayer() {
        return statusPlayer;
    }

    public void setStatusPlayer(StatusPlayer statusPlayer) {
        this.statusPlayer = statusPlayer;
    }

    public Socket getSocket() {
        return socket;
    }

    public void invalidate() {
        ServerLoader.invalidateSocket(socket);
    }
}
