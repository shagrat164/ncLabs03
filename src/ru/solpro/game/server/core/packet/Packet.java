/*
 * @(#)Packet.java 1.0 09.01.2017
 */

package ru.solpro.game.server.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Пакет данных.
 * @author Protsvetov Danila
 * @version 1.0
 */
public abstract class Packet {

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract short getId();
    public abstract void write(DataOutputStream dataOutputStream) throws IOException;
    public abstract void read(DataInputStream dataInputStream) throws IOException;
    public abstract void handle();
}
