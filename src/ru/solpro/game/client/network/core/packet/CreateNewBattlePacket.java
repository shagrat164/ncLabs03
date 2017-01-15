/*
 * @(#)CreateNewBattlePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Создание нового боя.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class CreateNewBattlePacket extends Packet {

//    private int player1Id;
//    private int player2Id;

    public CreateNewBattlePacket() {}

    public CreateNewBattlePacket(int player1Id, int player2Id) {
//        this.player1Id = player1Id;
//        this.player2Id = player2Id;
    }

    @Override
    public short getId() {
        return 10;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
//        dataOutputStream.writeInt(player1Id);
//        dataOutputStream.writeInt(player2Id);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {
//        Battle battle = new Battle(1, new Player(1, "noname"), new Player(2, "noname"));
//        GameOnlineController.setBattle(battle);
    }
}
