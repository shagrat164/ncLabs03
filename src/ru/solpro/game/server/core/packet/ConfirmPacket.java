/*
 * @(#)ConfirmPacket.java 1.0 15.01.2017
 */

package ru.solpro.game.server.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ConfirmPacket extends Packet {

    private boolean confirm;

    public ConfirmPacket() {}

    public ConfirmPacket(boolean confirm) {
        this.confirm = confirm;
    }

    @Override
    public short getId() {
        return 5;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeBoolean(confirm);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        confirm = dataInputStream.readBoolean();
    }

    @Override
    public void handle() {
        System.out.println("confirm:" + confirm);
    }
}
