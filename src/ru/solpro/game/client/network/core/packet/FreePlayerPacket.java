/*
 * @(#)PlayerListPacket.java 1.0 12.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Пакет получающий свободного игрока на сервере.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class FreePlayerPacket extends Packet {

    private int sizeList;
    private LinkedList<Integer> id;
    private LinkedList<String> nickname;

    public FreePlayerPacket() {
        id = new LinkedList<>();
        nickname = new LinkedList<>();
    }

    @Override
    public short getId() {
        return 4;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        sizeList = dataInputStream.readInt();
        for (int i = 0; i < sizeList; i++) {
            id.add(dataInputStream.readInt());
            nickname.add(dataInputStream.readUTF());
        }
    }

    @Override
    public void handle() {
        ClientLoader.getClientListLayoutController().getPlayers().clear();
        for (int i = 0; i < sizeList; i++) {
            Player player = new Player(id.get(i), nickname.get(i));
            ClientLoader.getClientListLayoutController().getPlayers().add(player);
        }
    }
}
