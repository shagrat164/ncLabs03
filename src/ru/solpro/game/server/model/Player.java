/*
 * @(#)Player.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

import java.net.Socket;

import static ru.solpro.game.server.model.StatusPlayer.FREE;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Player {

    private static int count;
    private int id;
    private String nickname;
    private Socket socket;
    private StatusPlayer statusPlayer;

    public Player(Socket socket) {
        count++;
        this.nickname = "noname";
        this.socket = socket;
        this.id = count;
        statusPlayer = FREE;
    }

    public Player(String nickname, Socket socket) {
        count++;
        this.nickname = nickname;
        this.socket = socket;
        this.id = count;
        statusPlayer = FREE;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Socket getSocket() {
        return socket;
    }

    public StatusPlayer getStatusPlayer() {
        return statusPlayer;
    }

    public void setStatusPlayer(StatusPlayer statusPlayer) {
        this.statusPlayer = statusPlayer;
    }
}
