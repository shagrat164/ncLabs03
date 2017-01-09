/*
 * @(#)AuthorisePacket.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Пакет аутентификации.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class AuthenticationPacket extends Packet {

    private String name;

    public AuthenticationPacket() {}

    public AuthenticationPacket(String name) {
        this.name = name;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(name);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {

    }
}
