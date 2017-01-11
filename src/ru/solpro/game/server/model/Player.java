/*
 * @(#)Player.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.net.Socket;

import static ru.solpro.game.server.model.StatusPlayer.FREE;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Player {

    private static int count;
    private int id;
    private StringProperty nickname;
    private ObjectProperty<StatusPlayer> statusPlayer;
    private Socket socket;

    public Player(String nickname, Socket socket) {
        count++;
        this.nickname = new SimpleStringProperty(nickname);
        this.socket = socket;
        this.id = count;
        this.statusPlayer = new SimpleObjectProperty<>(FREE);
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname.get();
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public Socket getSocket() {
        return socket;
    }

    public StatusPlayer getStatusPlayer() {
        return statusPlayer.get();
    }

    public void setStatusPlayer(StatusPlayer statusPlayer) {
        this.statusPlayer.set(statusPlayer);
    }

    public ObjectProperty<StatusPlayer> statusPlayerProperty() {
        return statusPlayer;
    }
}
