/*
 * @(#)Battle.java 1.0 05.01.2017
 */

package ru.solpro.game.client.network.model;

import ru.solpro.game.server.model.GameOnline;
import ru.solpro.game.server.model.Player;

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
    private ru.solpro.game.server.model.GameOnline game = new ru.solpro.game.server.model.GameOnline();

//    public Battle() {
//        count++;
//        this.id = count;
//    }
//
//    public Battle(Player player1) {
//        count++;
//        this.id = count;
//        this.player1 = player1;
//    }

    public Battle(Player player1, Player player2) {
        count++;
        this.id = count;
        this.player1 = player1;
        this.player2 = player2;
        startBattle();
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

    public void shot(int mouseX, int mouseY) {
        // Если курсор мыши внутри игрового поля противника
        if ((mouseX > 100) && (mouseY > 100) && (mouseX < 400) && (mouseY < 400)) {
            // Вычисляем номер строки в массиве
            int i = (mouseY - 100) / 30;
            // Вычисляем номер элемента в строке в массиве
            int j = (mouseX - 100) / 30;
            // Если не конец игры
            if (game.getGameStatus() == 0) {
                if (!game.isPlayer2Course()) { // ход player1
                    // Если ячейка подходит для выстрела
                    if ((game.getArrayPlayer2()[i][j] <= 4)
                            && (game.getArrayPlayer2()[i][j] >= -1)) {
                        // Производим выстрел
                        game.player1Shot(i, j);
                    }
                } else { // ход player2
                    // Если ячейка подходит для выстрела
                    if ((game.getArrayPlayer1()[i][j] <= 4)
                            && (game.getArrayPlayer1()[i][j] >= -1)) {
                        // Производим выстрел
                        game.player2Shot(i, j);
                    }
                }
            }
        }
    }
}
