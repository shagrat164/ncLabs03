/*
 * @(#)Battle.java 1.0 05.01.2017
 */

package ru.solpro.game.client.network.model;

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
    // кто ходит
    private boolean player2Course;
    // инициатор боя
    private int[][] arrayPlayer1;
    // присоединившийся игрок
    private int[][] arrayPlayer2;
    // статус
    private int status;

    public Battle(int id, Player player1, Player player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
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

    public boolean isPlayer2Course() {
        return player2Course;
    }

    public int[][] getArrayPlayer1() {
        return arrayPlayer1;
    }

    public int[][] getArrayPlayer2() {
        return arrayPlayer2;
    }

    public void setPlayer2Course(boolean player2Course) {
        this.player2Course = player2Course;
    }

    public void setArrayPlayer1(int[][] arrayPlayer1) {
        this.arrayPlayer1 = arrayPlayer1;
    }

    public void setArrayPlayer2(int[][] arrayPlayer2) {
        this.arrayPlayer2 = arrayPlayer2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
