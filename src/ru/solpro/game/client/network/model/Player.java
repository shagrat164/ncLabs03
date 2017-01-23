/*
 * @(#)Player.java 1.0 05.01.2017
 */

package ru.solpro.game.client.network.model;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Player {

    private int id;
    private String nickname;
    private StatusPlayer statusPlayer;

    public Player(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        this.statusPlayer = StatusPlayer.FREE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public StatusPlayer getStatusPlayer() {
        return statusPlayer;
    }

    public void setStatusPlayer(StatusPlayer statusPlayer) {
        this.statusPlayer = statusPlayer;
    }
}