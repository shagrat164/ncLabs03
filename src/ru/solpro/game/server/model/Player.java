/*
 * @(#)Player.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

import java.net.Socket;
import java.util.Random;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Player {

    private int id;
    private String nickname;
    private Socket socket;
//    private Battle battle;

    public Player(String nickname, Socket socket) {
        this.nickname = nickname;
        this.socket = socket;
        this.id = nickname.hashCode() * new Random().nextInt(1000);
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public Socket getSocket() {
        return socket;
    }

//    public Battle getBattle() {
//        return battle;
//    }
//
//    public void setBattle(Battle battle) {
//        this.battle = battle;
//    }
}
