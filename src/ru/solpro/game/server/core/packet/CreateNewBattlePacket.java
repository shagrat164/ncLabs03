/*
 * @(#)CreateNewBattlePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.server.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Создание боя.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class CreateNewBattlePacket extends Packet {

//    private int player1Id;
//    private int player2Id;

    public CreateNewBattlePacket() {}

    @Override
    public short getId() {
        return 10;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
//        player1Id = dataInputStream.readInt();
//        player2Id = dataInputStream.readInt();
    }

    @Override
    public void handle() {
//        Player player1 = ServerLoader.getPlayer(player1Id);
//        Player player2 = ServerLoader.getPlayer(player2Id);
//        if (player1 == null || player2 == null) {
//            LogServer.info("Невозможно создать бой. Пользователь не найден.");
//            return;
//        }
//        Battle battle = new Battle(player1, player2);
//        ServerLoader.getBattles().put(battle.getId(), battle);
    }
}
