/*
 * @(#)FreePlayerPacket.java 1.0 17.01.2017
 */
package ru.solpro.game.client.network.core.packet;

import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Пакет отправляет информацию о пользователе другому пользователю.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class FreePlayerPacket extends Packet {

    private short action; // 1-del, 2-add
    private int userId;
    private String nickname;

    public FreePlayerPacket() {}

    @Override
    public short getId() {
        return 6;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.action = dataInputStream.readShort();
        this.userId = dataInputStream.readInt();
        this.nickname = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        if (action == 1) {
            Player player = ClientLoader.getClientListLayoutController().getPlayer(nickname);
            ClientLoader.getClientListLayoutController().getPlayers().remove(player);
        } else if (action == 2) {
            Player player = new Player(userId, nickname);
            ClientLoader.getClientListLayoutController().getPlayers().add(player);
        }
    }
}
