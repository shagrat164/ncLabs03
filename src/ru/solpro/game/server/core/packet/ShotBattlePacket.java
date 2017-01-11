/*
 * @(#)ShotBattlePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.server.core.packet;

import ru.solpro.game.server.core.ServerLoader;

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
        ServerLoader.getBattle(id).shot(mouseX, mouseY);
        //TODO: отправть пакет участникам битвы id с инфой о битве
    }
}
