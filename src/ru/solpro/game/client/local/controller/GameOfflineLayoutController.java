/*
 * @(#)RootLayoutController.java 1.0 27.12.2016
 */

package ru.solpro.game.client.local.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Администратор on 27.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameOfflineLayoutController {

    private Image imageDeck;
    private Image imageKilled;
    private Image imageInjured;
    private Image imageEnd1;
    private Image imageEnd2;
    private Image imageBomb;
    private GameOfflineController gameOfflineController;

    @FXML
    private AnchorPane layoutObject;

    public GameOfflineLayoutController() {
        try {
            imageBomb = new Image(getClass().getResourceAsStream("..\\..\\img\\Bomb.png"));
            imageDeck = new Image(getClass().getResourceAsStream("..\\..\\img\\Deck.png"));
            imageInjured = new Image(getClass().getResourceAsStream("..\\..\\img\\Injured.png"));
            imageKilled = new Image(getClass().getResourceAsStream("..\\..\\img\\Killed.png"));
            imageEnd1 = new Image(getClass().getResourceAsStream("..\\..\\img\\End1.png"));
            imageEnd2 = new Image(getClass().getResourceAsStream("..\\..\\img\\End2.png"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paintComponent();
            }
        };
        timer.start();
    }

    public void setGameOfflineController(GameOfflineController gameOfflineController) {
        this.gameOfflineController = gameOfflineController;
    }

    /**
     * Отрисовка объектов
     */
    public void paintComponent() {
        // Очистка содержимого слоя
        layoutObject.getChildren().clear();

        // Если курсор мыши внутри игрового поля компьютера
        if ((gameOfflineController.getMouseX() > 100)
                && (gameOfflineController.getMouseY() > 100)
                && (gameOfflineController.getMouseX() < 400)
                && (gameOfflineController.getMouseY() < 400)) {
            // Если не конец игры и ход игрока
            if ((gameOfflineController.getGame().getGameStatus() == 0)
                    && (!gameOfflineController.getGame().isComputerCourse())) {
                // Вычисляем номер строки в массиве
                int i = (gameOfflineController.getMouseY() - 100) / 30;
                // Вычисляем номер элемента в строке в массиве
                int j = (gameOfflineController.getMouseX() - 100) / 30;
                // Если ячейка подходит для выстрела
                if ((gameOfflineController.getGame().getArrayComp()[i][j] <= 4)
                        && (gameOfflineController.getGame().getArrayComp()[i][j] >= -1)) {
                    // Рисуем квадрат с заливкой
                    Rectangle rectangle = new Rectangle(100 + j * 30, 100 + i * 30, 30, 30);
                    rectangle.setFill(Color.RED);
                    layoutObject.getChildren().add(rectangle);
                }
            }
        }

        // Отрисовка игровых полей Компьютера и Игрока на основании массивов
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Игровое поле компьютера
                if (gameOfflineController.getGame().getArrayComp()[i][j] != 0) {
                    // Если это подбитая палуба корабля
                    if ((gameOfflineController.getGame().getArrayComp()[i][j] >= 8)
                            && (gameOfflineController.getGame().getArrayComp()[i][j] <= 11)) {
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (gameOfflineController.getGame().getArrayComp()[i][j] >= 15) {
                        //Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((gameOfflineController.getGame().getArrayComp()[i][j] >= 5)
                            || (gameOfflineController.getGame().getArrayComp()[i][j] == -2)) {
                        ImageView imageView = new ImageView(imageBomb);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                }
                // Игровое поле игрока
                if (gameOfflineController.getGame().getArrayPlay()[i][j] != 0) {
                    // Если это палуба корабля
                    if ((gameOfflineController.getGame().getArrayPlay()[i][j] >= 1)
                            && (gameOfflineController.getGame().getArrayPlay()[i][j] <= 4)) {
                        ImageView imageView = new ImageView(imageDeck);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if ((gameOfflineController.getGame().getArrayPlay()[i][j] >= 8)
                            && (gameOfflineController.getGame().getArrayPlay()[i][j] <= 11)) {
                        // Если это подбитая палуба корабля
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (gameOfflineController.getGame().getArrayPlay()[i][j] >= 15) {
                        // Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((gameOfflineController.getGame().getArrayPlay()[i][j] >= 5)
                            || (gameOfflineController.getGame().getArrayPlay()[i][j] == -2)) {
                        ImageView imageView = new ImageView(imageBomb);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                }
            }
        }

        // Вывод изображения конца игры - при окончании игры
        if (gameOfflineController.getGame().getGameStatus() == 1) {
            // Если победил Игрок
            ImageView imageView = new ImageView(imageEnd1);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        } else if (gameOfflineController.getGame().getGameStatus() == 2) {
            // Если победил Компьютер
            ImageView imageView = new ImageView(imageEnd2);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        }
    }

    @FXML
    private void buttonNewGameAction(ActionEvent actionEvent) {
        System.out.println("new game");
        gameOfflineController.getGame().start();
    }

    @FXML
    private void buttonExitAction(ActionEvent actionEvent) {
        System.out.println("exit");
        System.exit(0);
    }
}
