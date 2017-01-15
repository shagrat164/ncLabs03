/*
 * @(#)GameOnlineLayoutController.java 1.0 02.01.2017
 */

package ru.solpro.game.client.network.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by danila on 02.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameOnlineLayoutController {


    private Image imageDeck;
    private Image imageKilled;
    private Image imageInjured;
    private Image imageEnd1;
    private Image imageEnd2;
    private Image imageBomb;
    private GameOnlineController gameOnlineController;
    @FXML
    private AnchorPane layoutObject;

    public GameOnlineLayoutController() {
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

    public void setGameOnlineController(GameOnlineController gameOnlineController) {
        this.gameOnlineController = gameOnlineController;
    }

    private void paintComponent() {
        // Очистка содержимого слоя
        layoutObject.getChildren().clear();

        // Если курсор мыши внутри игрового поля компьютера
        if ((gameOnlineController.getMouseX() > 100)
                && (gameOnlineController.getMouseY() > 100)
                && (gameOnlineController.getMouseX() < 400)
                && (gameOnlineController.getMouseY() < 400)) {
            // Если не конец игры и ход игрока
            if ((GameOnlineController.getBattle().getStatus() == 0)
                    && (!GameOnlineController.getBattle().isPlayer2Course())) {
                // Вычисляем номер строки в массиве
                int i = (gameOnlineController.getMouseY() - 100) / 30;
                // Вычисляем номер элемента в строке в массиве
                int j = (gameOnlineController.getMouseX() - 100) / 30;
                // Если ячейка подходит для выстрела
                if ((GameOnlineController.getBattle().getArrayPlayer2()[i][j] <= 4)
                        && (GameOnlineController.getBattle().getArrayPlayer2()[i][j] >= -1)) {
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
                if (GameOnlineController.getBattle().getArrayPlayer2()[i][j] != 0) {
                    // Если это подбитая палуба корабля
                    if ((GameOnlineController.getBattle().getArrayPlayer2()[i][j] >= 8)
                            && (GameOnlineController.getBattle().getArrayPlayer2()[i][j] <= 11)) {
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (GameOnlineController.getBattle().getArrayPlayer2()[i][j] >= 15) {
                        //Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((GameOnlineController.getBattle().getArrayPlayer2()[i][j] >= 5)
                            || (GameOnlineController.getBattle().getArrayPlayer2()[i][j] == -2)) {
                        ImageView imageView = new ImageView(imageBomb);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                }
                // Игровое поле игрока
                if (GameOnlineController.getBattle().getArrayPlayer1()[i][j] != 0) {
                    // Если это палуба корабля
                    if ((GameOnlineController.getBattle().getArrayPlayer1()[i][j] >= 1)
                            && (GameOnlineController.getBattle().getArrayPlayer1()[i][j] <= 4)) {
                        ImageView imageView = new ImageView(imageDeck);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if ((GameOnlineController.getBattle().getArrayPlayer1()[i][j] >= 8)
                            && (GameOnlineController.getBattle().getArrayPlayer1()[i][j] <= 11)) {
                        // Если это подбитая палуба корабля
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (GameOnlineController.getBattle().getArrayPlayer1()[i][j] >= 15) {
                        // Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((GameOnlineController.getBattle().getArrayPlayer1()[i][j] >= 5)
                            || (GameOnlineController.getBattle().getArrayPlayer1()[i][j] == -2)) {
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
        if (GameOnlineController.getBattle().getStatus() == 1) {
            // Если победил Игрок
            ImageView imageView = new ImageView(imageEnd1);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        } else if (GameOnlineController.getBattle().getStatus() == 2) {
            // Если победил Компьютер
            ImageView imageView = new ImageView(imageEnd2);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        }
    }
}
