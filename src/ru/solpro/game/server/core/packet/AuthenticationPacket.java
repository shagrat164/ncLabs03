/*
 * @(#)AuthorisePacket.java 1.0 09.01.2017
 */

package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.Client;
import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.core.datasrv.LogServer;

import java.io.*;

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
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        nickname = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        //TODO: добавить проверку на существующего игрока с ником

        Client client = ServerLoader.getHandler(getSocket());

        client.setNickname(nickname);
        ServerLoader.getRootLayoutController().getClients().add(client);

        LogServer.info(String.format("Успешная аутентификация. Игрок %s. Хост %s",
                client.getNickname(),
                getSocket().getInetAddress().getHostAddress()));

        // отправить инфу о новом входе пользователя
        // всем клиентам
//        ServerLoader.getHandlers().keySet().forEach(s -> ServerLoader.sendPacket(s, new AuthenticationPacket(player.getId(), player.getNickname())));
    }
}
