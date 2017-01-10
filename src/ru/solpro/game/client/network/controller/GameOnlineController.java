/*
 * @(#)GameOnlineController.java 1.0 02.01.2017
 */
package ru.solpro.game.client.network.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ru.solpro.game.client.network.model.GameOnline;

import java.io.IOException;

/**
 * Created by danila on 02.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameOnlineController {

    private GameOnline game;
    private int mouseX;
    private int mouseY;

    public GameOnlineController() {
        game = new GameOnline();
        game.start();
    }

    public GameOnline getGame() {
        return game;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public Scene createScene() {
        Scene result = new Scene(initLayout());

        result.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Если сделано одиночное нажатие левой клавишей мыши
                if ((event.getButton() == MouseButton.PRIMARY) && (event.getClickCount() == 1)) {
                    // Получаем текущие координаты курсора мыши
                    mouseX = (int) event.getX();
                    mouseY = (int) event.getY();
                    // Если курсор мыши внутри игрового поля компьютера
                    if ((mouseX > 100) && (mouseY > 100) && (mouseX < 400) && (mouseY < 400)) {
                        // Если не конец игры и ход игрока
                        if ((game.getGameStatus() == 0)
                                && (!game.isComputerCourse())) {
                            // Вычисляем номер строки в массиве
                            int i = (mouseY - 100) / 30;
                            // Вычисляем номер элемента в строке в массиве
                            int j = (mouseX - 100) / 30;
                            // Если ячейка подходит для выстрела
                            if ((game.getArrayComp()[i][j] <= 4)
                                    && (game.getArrayComp()[i][j] >= -1)) {
                                // Производим выстрел
                                game.playerShot(i, j);
                            }
                        }
                    }
                }
            }
        });

        result.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Получаем координаты курсора
                mouseX = (int) event.getX();
                mouseY = (int) event.getY();
                // Если курсор в области поля игрока
                if (mouseX >= 100 &&
                        mouseY >= 100 &&
                        mouseX <= 400 &&
                        mouseY <= 400 &&
                        game.getGameStatus() == 0) {
                    result.setCursor(Cursor.CROSSHAIR);
                } else {
                    result.setCursor(Cursor.DEFAULT);
                }
            }
        });

//        result.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                for (int i = 0; i < game.getArrayPlayer2().length; i++) {
//                    for (int j = 0; j < game.getArrayPlayer2()[i].length; j++) {
//                        System.out.print(game.getArrayPlayer2()[i][j] + "\t\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println("===========================");
//            }
//        });

        return result;
    }

    private AnchorPane initLayout() {
        AnchorPane result = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/GameOnlineLayout.fxml"));
        try {
            result = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameOnlineLayoutController controller = loader.getController();
        controller.setGameOnlineController(this);
        return result;
    }
}
