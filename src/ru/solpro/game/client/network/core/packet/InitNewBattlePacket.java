/*
 * @(#)InitNewBattlePacket.java 1.0 15.01.2017
 */
package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class InitNewBattlePacket extends Packet {
    @Override
    public short getId() {
        return 4;
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
