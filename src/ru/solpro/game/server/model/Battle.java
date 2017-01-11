/*
 * @(#)Battle.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Battle {

    private static int count;
    private int id;
    // инициатор боя
    private Player player1;
    // присоединившийся игрок
    private Player player2;
    // бой
    private GameOnline game = new GameOnline();

    public Battle() {
        count++;
        this.id = count;
    }

    public Battle(Player player1) {
        count++;
        this.id = count;
        this.player1 = player1;
    }

    // запуск боя
    public void startBattle() {
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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameOnline getGame() {
        return game;
    }
}
