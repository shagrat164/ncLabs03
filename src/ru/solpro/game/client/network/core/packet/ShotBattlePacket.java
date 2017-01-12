/*
 * @(#)ShotBattlePacket.java 1.0 10.01.2017
 */

package ru.solpro.game.client.network.core.packet;

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

    public ShotBattlePacket(int id, int mouseX, int mouseY) {
        this.id = id;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public short getId() {
        return 11;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(id);
        dataOutputStream.writeInt(mouseX);
        dataOutputStream.writeInt(mouseY);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {}

    @Override
    public void handle() {}
}
