/*
 * @(#)User.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

import java.io.Serializable;
import java.net.Socket;
import java.util.Random;

/**
 * Created by danila on 05.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Player implements Serializable {

    private Random random = new Random();
    private int id;
    private String name;
    private Socket socket;
    private Battle battle;

    public Player(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
        this.id = name.hashCode() * random.nextInt(1000);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Player player = (Player) o;

        return id == player.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
