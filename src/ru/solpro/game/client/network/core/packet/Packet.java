/*
 * @(#)Packet.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public abstract class Packet {
    public abstract short getId();
    public abstract void write(DataOutputStream dataOutputStream) throws IOException;
    public abstract void read(DataInputStream dataInputStream) throws IOException;
}
