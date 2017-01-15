/*
 * @(#)Battle.java 1.0 05.01.2017
 */

package ru.solpro.game.server.model;

import ru.solpro.game.server.core.Client;
import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.core.packet.StateBattlePacket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Battle {

    private static int count;

    private int id;
    // инициатор боя
    private Client player1;
    // присоединившийся игрок
    private Client player2;
    // бой
    private GameOnline game = new GameOnline();

    public Battle(Client player1, Client player2) {
        count++;
        this.id = count;
        this.player1 = player1;
        this.player2 = player2;
        this.game.start();

        ServerLoader.sendPacket(player1.getSocket(),
                new StateBattlePacket(id,
                        game.isPlayer2Course(),
                        game.getArrayPlayer1(),
                        game.getArrayPlayer2(),
                        game.getGameStatus()));
        ServerLoader.sendPacket(player2.getSocket(),
                new StateBattlePacket(id,
                        game.isPlayer2Course(),
                        game.getArrayPlayer1(),
                        game.getArrayPlayer2(),
                        game.getGameStatus()));
    }

    public int getId() {
        return id;
    }

    public Client getPlayer1() {
        return player1;
    }

    public Client getPlayer2() {
        return player2;
    }

    public void setPlayer1(Client player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Client player2) {
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
