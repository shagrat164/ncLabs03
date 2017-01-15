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

import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.core.packet.ShotBattlePacket;
import ru.solpro.game.client.network.model.Battle;

import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameOnlineController {

    private static Battle battle;
    private int mouseX;
    private int mouseY;

    public GameOnlineController() {}

    public static void setBattle(Battle battle) {
        GameOnlineController.battle = battle;
    }

    public static Battle getBattle() {
        return battle;
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
                        //TODO: отправить пакет выстрела на сервер
                        ClientLoader.sendPacket(new ShotBattlePacket(battle.getId(), mouseX, mouseY));
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
                        mouseY <= 400 /*&&
                        battle.getGameStatus() == 0*/) {
                    result.setCursor(Cursor.CROSSHAIR);
                } else {
                    result.setCursor(Cursor.DEFAULT);
                }
            }
        });

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
