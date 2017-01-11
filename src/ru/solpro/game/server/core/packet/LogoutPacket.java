/*
 * @(#)LogoutPacket.java 1.0 09.01.2017
 */
package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.core.datasrv.LogServer;

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
    public void read(DataInputStream dataInputStream) throws IOException {
        nickname = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        ServerLoader.getHandler(getSocket()).invalidate();
//        System.out.println("Logout: " + nickname);
        LogServer.info(String.format("Выход пользователя. Игрок %s. Хост %s", nickname, getSocket().getInetAddress().getHostAddress()));
    }
}
