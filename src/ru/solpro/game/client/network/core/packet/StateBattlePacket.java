/*
 * @(#)StateBattlefieldPacket.java 1.0 10.01.2017
 */
package ru.solpro.game.client.network.core.packet;

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
    // инициатор боя
    private int[][] arrayPlayer1;
    // присоединившийся игрок
    private int[][] arrayPlayer2;

    public StateBattlePacket() {}

    @Override
    public short getId() {
        return 12;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.id = dataInputStream.readInt();
        this.player2Course = dataInputStream.readBoolean();
        for (int i = 0; i < arrayPlayer1.length; i++) {
            for (int j = 0; j < arrayPlayer1[i].length; j++) {
                arrayPlayer1[i][j] = dataInputStream.readInt();
            }
        }
        for (int i = 0; i < arrayPlayer2.length; i++) {
            for (int j = 0; j < arrayPlayer2[i].length; j++) {
                arrayPlayer2[i][j] = dataInputStream.readInt();
            }
        }
    }

    @Override
    public void handle() {
        //TODO: запись данных в объект
    }
}
