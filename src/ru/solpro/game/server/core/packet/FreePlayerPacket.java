/*
 * @(#)FreePlayerPacket.java 1.0 17.01.2017
 */
package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.model.StatusPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Пакет отправляет информацию о пользователе другому пользователю.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class FreePlayerPacket extends Packet {

    private short action; // 1-del, 2-add
    private int userId;
    private String nickname;

    public FreePlayerPacket() {}

    public FreePlayerPacket(short action, int userId, String nickname) {
        this.action = action;
        this.userId = userId;
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 6;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(action);
        dataOutputStream.writeInt(userId);
        dataOutputStream.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {}
}
