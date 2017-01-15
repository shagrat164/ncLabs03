/*
 * @(#)Packet.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public abstract class Packet {

    /**
     * Получить id пакета.
     * @return id пакета.
     */
    public abstract short getId();

    /**
     * запись данных в поток.
     * @param dataOutputStream поток данных.
     * @throws IOException ошибка ввода/вывода.
     */
    public abstract void write(DataOutputStream dataOutputStream) throws IOException;

    /**
     * Чтение данных из потока.
     * @param dataInputStream поток данных.
     * @throws IOException ошибка ввода/вывода.
     */
    public abstract void read(DataInputStream dataInputStream) throws IOException;

    /**
     * Тело пакета. Выполнение различных действий в зависимости от пакета.
     */
    public abstract void handle();
}
