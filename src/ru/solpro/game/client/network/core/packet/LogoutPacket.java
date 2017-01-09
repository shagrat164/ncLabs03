/*
 * @(#)LogoutPacket.java 1.0 09.01.2017
 */
package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class LogoutPacket extends Packet {

    private String nickname;

    public LogoutPacket(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {}
}
