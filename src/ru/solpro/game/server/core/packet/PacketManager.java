/*
 * @(#)PacketManager.java 1.0 09.01.2017
 */

package ru.solpro.game.server.core.packet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class PacketManager {

    private static final Map<Short, Class<? extends Packet>> packets = new HashMap<>();

    //TODO: переделать на рефлексию
    static {
        packets.put((short) 1, AuthenticationPacket.class);
        packets.put((short) 2, LogoutPacket.class);
    }

    public static Packet getPacket(short id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
