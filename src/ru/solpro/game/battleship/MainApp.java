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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.solpro.game.battleship.model.Game;

/**
 * Морской бой.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MainApp extends Application {

    private Game game;
    private Stage primaryStage;
    private GameLayoutController gameLayoutController;

    /**
     * Главный слой для окна.
     */
    private BorderPane rootLayout;

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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public Game getGame() {
        return game;
    }

    /**
     * Инициализация приложения.
     */
    private void initApp() {
        game = new Game();
        game.start();
    }

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
                gameLayoutController.paintComponent();
            }
        };
        timer.start();
    }

    private void initLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/GameLayout.fxml"));
        rootLayout = loader.load();

        gameLayoutController = loader.getController();
        gameLayoutController.setMainApp(this);
        gameLayoutController.init();
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
}
