/*
 * @(#)StateBattlefieldPacket.java 1.0 10.01.2017
 */
package ru.solpro.game.server.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Состояние поля боя.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class StateBattlePacket extends Packet {


    // инициатор боя
    private int[][] arrayPlayer1;
    // присоединившийся игрок
    private int[][] arrayPlayer2;

    @Override
    public short getId() {
        return 12;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {

    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {

    }
}
