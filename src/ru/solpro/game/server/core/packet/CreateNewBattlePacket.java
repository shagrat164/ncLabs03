/*
 * @(#)CreateNewBattlePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.server.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class CreateNewBattlePacket extends Packet {

    public CreateNewBattlePacket() {}

    @Override
    public short getId() {
        return 10;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {

    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {

    }

    @Override
    public void handle() {

    }
}
