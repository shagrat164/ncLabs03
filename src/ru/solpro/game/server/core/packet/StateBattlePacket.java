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

    // id битвы
    private int id;
    // кто ходит
    private boolean player2Course;
    // игрок 1
    private int[][] arrayPlayer1;
    // игрок 2
    private int[][] arrayPlayer2;
    // статус игры
    private int status;

    public StateBattlePacket(int id, boolean player2Course, int[][] arrayPlayer1, int[][] arrayPlayer2, int status) {
        this.id = id;
        this.player2Course = player2Course;
        this.arrayPlayer1 = arrayPlayer1;
        this.arrayPlayer2 = arrayPlayer2;
        this.status = status;
    }

    @Override
    public short getId() {
        return 12;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(id);
        dataOutputStream.writeBoolean(player2Course);
        for (int i = 0; i < arrayPlayer1.length; i++) {
            for (int j = 0; j < arrayPlayer1[i].length; j++) {
                dataOutputStream.writeInt(arrayPlayer1[i][j]);
            }
        }
        for (int i = 0; i < arrayPlayer2.length; i++) {
            for (int j = 0; j < arrayPlayer2[i].length; j++) {
                dataOutputStream.writeInt(arrayPlayer2[i][j]);
            }
        }
        dataOutputStream.writeInt(status);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {}
}
