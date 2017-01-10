/*
 * @(#)Battle.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

import java.util.Random;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Battle {
    private int id;
    // инициатор боя
    private Player player1;
    // присоединившийся игрок
    private Player player2;
    // бой
    private GameOnline game = new GameOnline();

    public Battle() {
        this.id = super.hashCode() + new Random().nextInt(1000);
    }

    public Battle(Player player1) {
        this.player1 = player1;
        this.id = super.hashCode() + this.player1.hashCode() + new Random().nextInt(1000);
    }

    // запуск боя
    public void startGame() {
        this.game.start();
    }

    public int getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameOnline getGame() {
        return game;
    }
}
