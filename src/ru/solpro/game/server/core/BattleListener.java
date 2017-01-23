/*
 * @(#)BattleListener.java 1.0 17.01.2017
 */

package ru.solpro.game.server.core;

import ru.solpro.game.server.model.Battle;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class BattleListener implements Runnable {

    private Client player1;
    private Client player2;
    private Battle battle;

    public BattleListener(Client player1, Client player2) {
        this.player1 = player1;
        this.player2 = player2;
        battle = new Battle(player1, player2);
    }

    @Override
    public void run() {
        while (true) {

        }
    }
}
