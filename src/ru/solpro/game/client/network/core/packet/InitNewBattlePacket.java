/*
 * @(#)InitNewBattlePacket.java 1.0 15.01.2017
 */
package ru.solpro.game.client.network.core.packet;

import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Запрос на инициализацию нового боя.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class InitNewBattlePacket extends Packet {

    private String nickname1; // инициатор запроса
    private String nickname2; // второй игрок

    public InitNewBattlePacket() {}

    public InitNewBattlePacket(String nickname1, String nickname2) {
        this.nickname1 = nickname1;
        this.nickname2 = nickname2;
    }

    @Override
    public short getId() {
        return 4;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(nickname1);
        dataOutputStream.writeUTF(nickname2);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        nickname1 = dataInputStream.readUTF();
        nickname2 = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        //TODO: сообщение с запросом на бой
        ClientLoader.getClientListLayoutController().initNewBattleWinShow(nickname1, nickname2);
    }
}
