/*
 * @(#)Battle.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Battle {
    private Player player1;
    private Player player2;
    private GameOnline game = new GameOnline();
//    private

    public Battle() {
    }

    public Battle(Player player1) {
        this.player1 = player1;
    }
}
