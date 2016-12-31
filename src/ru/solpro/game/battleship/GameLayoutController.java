/*
 * @(#)RootLayoutController.java 1.0 27.12.2016
 */

package ru.solpro.game.battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.solpro.game.battleship.model.Game;

/**
 * Created by Администратор on 27.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameLayoutController {

    private Game game;

    private Image imageDeck;
    private Image imageKilled;
    private Image imageInjured;
    private Image imageEnd1;
    private Image imageEnd2;
    private Image imageBomb;
    private AnchorPane layoutObject;
    private MainApp mainApp;
    @FXML
    private Button buttonAutoPlaceShips;
    @FXML
    private Button buttonStartGame;
    @FXML
    private Button buttonEndGame;

    public GameLayoutController() {
        layoutObject = new AnchorPane();
        try {
            imageBomb = new Image(getClass().getResourceAsStream("img\\Bomb.png"));
            imageDeck = new Image(getClass().getResourceAsStream("img\\Deck.png"));
            imageInjured = new Image(getClass().getResourceAsStream("img\\Injured.png"));
            imageKilled = new Image(getClass().getResourceAsStream("img\\Killed.png"));
            imageEnd1 = new Image(getClass().getResourceAsStream("img\\End1.png"));
            imageEnd2 = new Image(getClass().getResourceAsStream("img\\End2.png"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        mainApp.getRootLayout().getChildren().add(layoutObject);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Отрисовка объектов
     */
    public void paintComponent() {
        // Очистка содержимого слоя
        layoutObject.getChildren().clear();

        // Если курсор мыши внутри игрового поля компьютера
        if ((mainApp.getMouseX() > 100)
                && (mainApp.getMouseY() > 100)
                && (mainApp.getMouseX() < 400)
                && (mainApp.getMouseY() < 400)) {
            // Если не конец игры и ход игрока
            if ((mainApp.getGame().getGameStatus() == 0)
                    && (!mainApp.getGame().isComputerCourse())) {
                // Вычисляем номер строки в массиве
                int i = (mainApp.getMouseY() - 100) / 30;
                // Вычисляем номер элемента в строке в массиве
                int j = (mainApp.getMouseX() - 100) / 30;
                // Если ячейка подходит для выстрела
                if ((mainApp.getGame().getArrayComp()[i][j] <= 4)
                        && (mainApp.getGame().getArrayComp()[i][j] >= -1)) {
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
                if (mainApp.getGame().getArrayComp()[i][j] != 0) {
                    // Если это подбитая палуба корабля
                    if ((mainApp.getGame().getArrayComp()[i][j] >= 8)
                            && (mainApp.getGame().getArrayComp()[i][j] <= 11)) {
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (mainApp.getGame().getArrayComp()[i][j] >= 15) {
                        //Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((mainApp.getGame().getArrayComp()[i][j] >= 5)
                            || (mainApp.getGame().getArrayComp()[i][j] == -2)) {
                        ImageView imageView = new ImageView(imageBomb);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                }
                // Игровое поле игрока
                if (mainApp.getGame().getArrayPlay()[i][j] != 0) {
                    // Если это палуба корабля
                    if ((mainApp.getGame().getArrayPlay()[i][j] >= 1)
                            && (mainApp.getGame().getArrayPlay()[i][j] <= 4)) {
                        ImageView imageView = new ImageView(imageDeck);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if ((mainApp.getGame().getArrayPlay()[i][j] >= 8)
                            && (mainApp.getGame().getArrayPlay()[i][j] <= 11)) {
                        // Если это подбитая палуба корабля
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (mainApp.getGame().getArrayPlay()[i][j] >= 15) {
                        // Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if ((mainApp.getGame().getArrayPlay()[i][j] >= 5)
                            || (mainApp.getGame().getArrayPlay()[i][j] == -2)) {
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
        if (mainApp.getGame().getGameStatus() == 1) {
            // Если победил Игрок
            ImageView imageView = new ImageView(imageEnd1);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        } else if (mainApp.getGame().getGameStatus() == 2) {
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
        mainApp.getGame().start();
    }

    @FXML
    public void buttonExitAction(ActionEvent actionEvent) {
        System.out.println("exit");
        System.exit(0);
    }

    public void buttonAutoPlaceShipsAction(ActionEvent actionEvent) {

    }

    public void buttonStartGameAction(ActionEvent actionEvent) {

    }

    public void buttonEndGameAction(ActionEvent actionEvent) {

    }

//    @FXML
//    public void buttonNewAction(ActionEvent actionEvent) {
//        Text text = new Text(10, 20, "text");
//        text.setFont(new Font(30));
//        AnchorPane ancorPane = new AnchorPane();
//        ancorPane.getChildren().add(text);
//
//        Scene scene = new Scene(ancorPane, 900, 600);
//
//        mainApp.getPrimaryStage().setScene(scene);
//    }
}
