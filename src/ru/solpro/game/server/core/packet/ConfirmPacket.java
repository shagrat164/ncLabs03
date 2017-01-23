/*
 * @(#)ConfirmPacket.java 1.0 15.01.2017
 */

package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.Client;
import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.model.Battle;
import ru.solpro.game.server.model.Player;

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

    @Override
    public short getId() {
        return 5;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        confirm = dataInputStream.readBoolean();
        nickname = dataInputStream.readUTF();
        nickname2 = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        if (confirm) {
            Client player1 = ServerLoader.getHandler(nickname);
            Client player2 = ServerLoader.getHandler(nickname2);
            // создаю новый бой
            Battle battle = new Battle(player1, player2);
            ServerLoader.getBattles().put(battle.getId(), battle);
        }
    }
}
