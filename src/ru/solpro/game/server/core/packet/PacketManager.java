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

    //TODO: переделать на рефлексию если будет время
    static {
        packets.put((short) 1, AuthenticationPacket.class);
        packets.put((short) 2, LogoutPacket.class);
        // дополнительные пакеты
//        packets.put((short) 3, MessagePacket.class);
        packets.put((short) 6, FreePlayerPacket.class);
        // пакеты относящиеся к битве
        packets.put((short) 4, InitNewBattlePacket.class);      // запрос на бой
        packets.put((short) 5, ConfirmPacket.class);            // yes/no
        packets.put((short) 10, CreateNewBattlePacket.class);   // новый бой
        packets.put((short) 11, ShotBattlePacket.class);        // выстрел
        packets.put((short) 12, StateBattlePacket.class);       // состояние битвы
    }

    private PacketManager() {}

    public static Packet getPacket(short id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
