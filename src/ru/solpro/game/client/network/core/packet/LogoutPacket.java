/*
 * @(#)LogoutPacket.java 1.0 09.01.2017
 */
package ru.solpro.game.client.network.core.packet;

import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class LogoutPacket extends Packet {

    private String nickname;

    public LogoutPacket() {}

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {
//        Player player = ClientLoader.getClientListLayoutController().getPlayer(nickname);
//        if (player != null) {
//            ClientLoader.getClientListLayoutController().getPlayers().remove(player);
//        }
    }
}
