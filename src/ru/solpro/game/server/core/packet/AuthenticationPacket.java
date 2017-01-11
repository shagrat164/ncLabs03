/*
 * @(#)AuthorisePacket.java 1.0 09.01.2017
 */

package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.core.datasrv.LogServer;
import ru.solpro.game.server.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class AuthenticationPacket extends Packet {

    private String nickname;

    public AuthenticationPacket() {}

    public AuthenticationPacket(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {}

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        nickname = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        //TODO: добавить проверку на существующего игрока с ником
        Player player = new Player(nickname, getSocket());

        ServerLoader.getPlayers().put(getSocket(), player);
        ServerLoader.getRootLayoutController().getPlayers().add(player);
        LogServer.info(String.format("Успешная аутентификация. Игрок %s. Хост %s", nickname, getSocket().getInetAddress().getHostAddress()));
    }
}
