/*
 * @(#)MessagePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MessagePacket extends Packet {

    private String nickname;
    private String message;

    public MessagePacket() {
    }

    public MessagePacket(String nickname, String message) {
        this.nickname = nickname;
        this.message = message;
    }

    @Override
    public short getId() {
        return 3;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        nickname = dataInputStream.readUTF();
        message = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        System.out.println(nickname + ": " + message);
    }
}
