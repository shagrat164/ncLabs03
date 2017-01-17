/*
 * @(#)LogoutPacket.java 1.0 09.01.2017
 */
package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.Client;
import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.core.datasrv.LogServer;
import ru.solpro.game.server.model.StatusPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class LogoutPacket extends Packet {

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
        Client client = ServerLoader.getHandler(getSocket());

        LogServer.info(String.format("Выход пользователя. Игрок %s. Хост %s",
                client.getNickname(),
                getSocket().getInetAddress().getHostAddress()));

        // TODO: запилить в отдельном потоке постоянное обновление
        // отправить инфу о выходе пользователя
        // всем клиентам
        ServerLoader.getHandlers().keySet().forEach(s ->
                ServerLoader.sendPacket(s,
                        new FreePlayerPacket((short) 1,
                                client.getUserId(),
                                client.getNickname())));

        ServerLoader.getRootLayoutController().getClients().remove(client);

        // удаление из списка игроков на сервере
        client.invalidate();

        // известить всех пользователей о выходе игрока
//        ServerLoader.getHandlers().keySet().forEach(s -> ServerLoader.sendPacket(s, new LogoutPacket(player.getNickname())));
    }
}
