/*
 * @(#)ShotBattlePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.model.Battle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ShotBattlePacket extends Packet {

    /**
     * id битвы
     */
    private int id;
    /**
     * Координата X
     */
    private int mouseX;
    /**
     * Координата Y
     */
    private int mouseY;

    public ShotBattlePacket() {}

    @Override
    public short getId() {
        return 11;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        id = dataInputStream.readInt();
        mouseX = dataInputStream.readInt();
        mouseY = dataInputStream.readInt();
    }

    @Override
    public void handle() {
        Battle battle = ServerLoader.getBattle(id);
        battle.shot(mouseX, mouseY);

        ServerLoader.sendPacket(battle.getPlayer1().getSocket(),
                new StateBattlePacket(id,
                        battle.getGame().isPlayer2Course(),
                        battle.getGame().getArrayPlayer1(),
                        battle.getGame().getArrayPlayer2(),
                        battle.getGame().getGameStatus()));
        ServerLoader.sendPacket(battle.getPlayer2().getSocket(),
                new StateBattlePacket(id,
                        battle.getGame().isPlayer2Course(),
                        battle.getGame().getArrayPlayer1(),
                        battle.getGame().getArrayPlayer2(),
                        battle.getGame().getGameStatus()));
    }
}
