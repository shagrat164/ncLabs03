/*
 * @(#)Battleship.java 1.0 22.12.2016
 */

package ru.solpro.game.battleship;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Морской бой.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MainAppClient extends Application {

    private Image imageDeck;
    private Image imageKilled;
    private Image imageInjured;
    private Image imageEnd1;
    private Image imageEnd2;
    private Image imageBomb;
    private Game game;
    private Stage primaryStage;

    /**
     * Главный слой для окна.
     */
    private BorderPane rootLayout;

    /**
     * Слой для динамического отображения объектов в окне.
     * Корабли, палубы, бомбы.
     */
    private AnchorPane layoutObject;

    /**
     * Координата указателя X.
     */
    private int mouseX;

    /**
     * Координата указателя Y.
     */
    private int mouseY;

    /**
     * Точка входа.
     * @param args    аргументы.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Инициализация переменных.
     */
    private void initApp() {
        game = new Game();
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
        game.start();
    }

    /**
     * Запуск приложение.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        initApp();
        initLayout();

        Scene scene = createScene();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Морской бой");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paintComponent();
            }
        };
        timer.start();
    }

    private void initLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RootLayout.fxml"));
        rootLayout = loader.load();
        rootLayout.getChildren().add(layoutObject);

        RootLayoutController rootLayoutController = loader.getController();
        rootLayoutController.setMainAppClient(this);
    }

    /**
     * Создание главной сцены.
     * @return Сцена.
     * @throws Exception Исключение.
     */
    private Scene createScene() {
        Scene result = new Scene(rootLayout);

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
                        if ((game.getGameStatus() == 0) && (!game.isComputerCourse())) {
                            // Вычисляем номер строки в массиве
                            int i = (mouseY - 100) / 30;
                            // Вычисляем номер элемента в строке в массиве
                            int j = (mouseX - 100) / 30;
                            // Если ячейка подходит для выстрела
                            if (game.getArrayComp()[i][j] <= 4 && game.getArrayComp()[i][j] >= -1) {
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
//                for (int i = 0; i < game.getArrayComp().length; i++) {
//                    for (int j = 0; j < game.getArrayComp()[i].length; j++) {
//                        System.out.print(game.getArrayComp()[i][j] + "\t\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println("===========================");
//            }
//        });

        return result;
    }

    /**
     * Отрисовка объектов
     */
    public void paintComponent() {
        // Очистка содержимого слоя
        layoutObject.getChildren().clear();

        // Если курсор мыши внутри игрового поля компьютера
        if ((mouseX > 100) && (mouseY > 100) && (mouseX < 400) && (mouseY < 400)) {
            // Если не конец игры и ход игрока
            if ((game.getGameStatus() == 0) && (game.isComputerCourse() == false)) {
                // Вычисляем номер строки в массиве
                int i = (mouseY - 100) / 30;
                // Вычисляем номер элемента в строке в массиве
                int j = (mouseX - 100) / 30;
                // Если ячейка подходит для выстрела
                if (game.getArrayComp()[i][j] <= 4 && game.getArrayComp()[i][j] >= -1) {
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
                if (game.getArrayComp()[i][j] != 0) {
                    // Если это подбитая палуба корабля
                    if ((game.getArrayComp()[i][j] >= 8) && (game.getArrayComp()[i][j] <= 11)) {
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    } else if (game.getArrayComp()[i][j] >= 15) { //Если это палуба полностью подбитого корабля
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if (game.getArrayComp()[i][j] >= 5 || game.getArrayComp()[i][j] == -2) {
                        ImageView imageView = new ImageView(imageBomb);
                        imageView.setTranslateX(100 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                }
                // Игровое поле игрока
                if (game.getArrayPlay()[i][j] != 0) {
                    // Если это палуба корабля
                    if ((game.getArrayPlay()[i][j] >= 1) && (game.getArrayPlay()[i][j] <= 4)) {
                        ImageView imageView = new ImageView(imageDeck);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если это подбитая палуба корабля
                    else if ((game.getArrayPlay()[i][j] >= 8) && (game.getArrayPlay()[i][j] <= 11)) {
                        ImageView imageView = new ImageView(imageInjured);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если это палуба полностью подбитого корабля
                    else if (game.getArrayPlay()[i][j] >= 15) {
                        ImageView imageView = new ImageView(imageKilled);
                        imageView.setTranslateX(500 + j * 30);
                        imageView.setTranslateY(100 + i * 30);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        layoutObject.getChildren().add(imageView);
                    }
                    // Если был выстрел
                    if (game.getArrayPlay()[i][j] >= 5 || game.getArrayPlay()[i][j] == -2) {
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
        if (game.getGameStatus() == 1) {// Если победил Игрок
            ImageView imageView = new ImageView(imageEnd1);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        } else if (game.getGameStatus() == 2) {// Если победил Компьютер
            ImageView imageView = new ImageView(imageEnd2);
            imageView.setTranslateX(300);
            imageView.setTranslateY(200);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            layoutObject.getChildren().add(imageView);
        }
    }

    public Game getGame() {
        return game;
    }
}
