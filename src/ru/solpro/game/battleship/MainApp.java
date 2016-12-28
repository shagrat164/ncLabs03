/*
 * @(#)Battleship.java 1.0 22.12.2016
 */

package ru.solpro.game.battleship;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MainApp extends Application {

    static Battle battle;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = createScene();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Морской бой");
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
        timer.start();
    }

    private Scene createScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
        Scene result = new Scene(root);
        battle = new Battle();

        result.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Если сделано одиночное нажатие левой клавишей мыши
                if ((event.getButton() == MouseButton.PRIMARY) && (event.getClickCount() == 1)) {
                    // Получаем текущие координаты курсора мыши
                    int mX = (int) event.getX();
                    int mY = (int) event.getY();
                    battle.setmX(mX);
                    battle.setmY(mY);
                    // Если курсор мыши внутри игрового поля компьютера
                    if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
                        // Если не конец игры и ход игрока
                        if ((battle.getMyGame().endg == 0) && (!battle.getMyGame().compHod)) {
                            // Вычисляем номер строки в массиве
                            int i = (mY - 100) / 30;
                            // Вычисляем номер элемента в строке в массиве
                            int j = (mX - 100) / 30;
                            // Если ячейка подходит для выстрела
                            if (battle.getMyGame().masComp[i][j] <= 4 && battle.getMyGame().masComp[i][j] >= -1) {
                                // Производим выстрел
                                battle.getMyGame().vistrelPlay(i, j);
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
                int mX = (int) event.getX();
                int mY = (int) event.getY();
                // Если курсор в области поля игрока
                if ((mX >= 100) && (mY >= 100) && (mX <= 400) && (mY <= 400)) {
                    result.setCursor(Cursor.CROSSHAIR);
                } else {
                    result.setCursor(Cursor.DEFAULT);
                }
            }
        });

        result.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < battle.getMyGame().masComp.length; i++) {
                    for (int j = 0; j < battle.getMyGame().masComp[i].length; j++) {
                        System.out.print(battle.getMyGame().masComp[i][j] + "\t\t");
                    }
                    System.out.println();
                }
                System.out.println("===========================");
            }
        });

        return result;
    }

    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
