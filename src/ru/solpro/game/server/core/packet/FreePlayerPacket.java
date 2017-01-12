/*
 * @(#)PlayerListPacket.java 1.0 12.01.2017
 */
package ru.solpro.game.server.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Пакет отправляющий список свободных игроков на сервере.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class FreePlayerPacket extends Packet {

    private int sizeList;
    private LinkedList<Integer> id;
    private LinkedList<String> nickname;

    public FreePlayerPacket() {}

    public FreePlayerPacket(LinkedList<Integer> id, LinkedList<String> nickname) {
        this.sizeList = id.size();
        this.id = id;
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 4;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(sizeList);
        for (int i = 0; i < sizeList; i++) {
            dataOutputStream.writeInt(id.get(i));
            dataOutputStream.writeUTF(nickname.get(i));
        }
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {}
}
