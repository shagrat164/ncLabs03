/*
 * @(#)ConfirmPacket.java 1.0 15.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ConfirmPacket extends Packet {

    private boolean confirm;
    private String nickname;
    private String nickname2;

    public ConfirmPacket() {}

    public ConfirmPacket(boolean confirm) {
        this.confirm = confirm;
    }

    public ConfirmPacket(boolean confirm, String nickname, String nickname2) {
        this.confirm = confirm;
        this.nickname = nickname;
        this.nickname2 = nickname2;
    }

    @Override
    public short getId() {
        return 5;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeBoolean(confirm);
        dataOutputStream.writeUTF(nickname);
        dataOutputStream.writeUTF(nickname2);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {
        System.out.println("confirm:" + confirm);
    }
}
