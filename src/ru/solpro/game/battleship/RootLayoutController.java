/*
 * @(#)RootLayoutController.java 1.0 27.12.2016
 */

package ru.solpro.game.battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Администратор on 27.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class RootLayoutController {

    private Image imageDeck;
    private Image imageKilled;
    private Image imageInjured;
    private Image imageEnd1;
    private Image imageEnd2;
    private Image imageBomb;
    private AnchorPane layoutObject;
    private MainAppClient mainAppClient;

    public RootLayoutController() {
        layoutObject = new AnchorPane();
        try {
            imageBomb = new Image(new FileInputStream("res\\Bomb.png"));
            imageDeck = new Image(new FileInputStream("res\\Deck.png"));
            imageInjured = new Image(new FileInputStream("res\\Injured.png"));
            imageKilled = new Image(new FileInputStream("res\\Killed.png"));
            imageEnd1 = new Image(new FileInputStream("res\\End1.png"));
            imageEnd2 = new Image(new FileInputStream("res\\End2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        mainAppClient.getRootLayout().getChildren().add(layoutObject);
    }

    public void setMainAppClient(MainAppClient mainAppClient) {
        this.mainAppClient = mainAppClient;
    }

    /**
     * Отрисовка объектов
     */
    public void paintComponent() {
        // Очистка содержимого слоя
        layoutObject.getChildren().clear();

        // Если курсор мыши внутри игрового поля компьютера
        if ((mainAppClient.getMouseX() > 100)
                && (mainAppClient.getMouseY() > 100)
                && (mainAppClient.getMouseX() < 400)
                && (mainAppClient.getMouseY() < 400)) {
            // Если не конец игры и ход игрока
            if ((mainAppClient.getGame().getGameStatus() == 0)
                    && (!mainAppClient.getGame().isComputerCourse())) {
                // Вычисляем номер строки в массиве
                int i = (mainAppClient.getMouseY() - 100) / 30;
                // Вычисляем номер элемента в строке в массиве
                int j = (mainAppClient.getMouseX() - 100) / 30;
                // Если ячейка подходит для выстрела
                if ((mainAppClient.getGame().getArrayComp()[i][j] <= 4)
                        && (mainAppClient.getGame().getArrayComp()[i][j] >= -1)) {
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
                if (mainAppClient.getGame().getArrayComp()[i][j] != 0) {
                    // Если это подбитая палуба корабля
                    if ((mainAppClient.getGame().getArrayComp()[i][j] >= 8)
                            && (mainAppClient.getGame().getArrayComp()[i][j] <= 11)) {
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (mainAppClient.getGame().getArrayComp()[i][j] >= 15) {
                        //Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((mainAppClient.getGame().getArrayComp()[i][j] >= 5)
                            || (mainAppClient.getGame().getArrayComp()[i][j] == -2)) {
                        ImageView imageView = new ImageView(imageBomb);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                }
                // Игровое поле игрока
                if (mainAppClient.getGame().getArrayPlay()[i][j] != 0) {
                    // Если это палуба корабля
                    if ((mainAppClient.getGame().getArrayPlay()[i][j] >= 1)
                            && (mainAppClient.getGame().getArrayPlay()[i][j] <= 4)) {
                        ImageView imageView = new ImageView(imageDeck);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if ((mainAppClient.getGame().getArrayPlay()[i][j] >= 8)
                            && (mainAppClient.getGame().getArrayPlay()[i][j] <= 11)) {
                        // Если это подбитая палуба корабля
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (mainAppClient.getGame().getArrayPlay()[i][j] >= 15) {
                        // Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((mainAppClient.getGame().getArrayPlay()[i][j] >= 5)
                            || (mainAppClient.getGame().getArrayPlay()[i][j] == -2)) {
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
        if (mainAppClient.getGame().getGameStatus() == 1) {
            // Если победил Игрок
            ImageView imageView = new ImageView(imageEnd1);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        } else if (mainAppClient.getGame().getGameStatus() == 2) {
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
    public void buttonNewGameAction(ActionEvent actionEvent) {
        System.out.println("new game");
        mainAppClient.getGame().start();
    }

    @FXML
    public void buttonExitAction(ActionEvent actionEvent) {
        System.out.println("exit");
        System.exit(0);
    }
}
