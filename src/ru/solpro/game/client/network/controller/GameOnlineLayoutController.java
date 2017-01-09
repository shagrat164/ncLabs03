/*
 * @(#)GameOnlineLayoutController.java 1.0 02.01.2017
 */
package ru.solpro.game.client.network.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Created by danila on 02.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameOnlineLayoutController {

    private GameOnlineController gameOnlineController;
    @FXML
    private Button buttonAutoPlaceShips;
    @FXML
    private Button buttonStartGame;
    @FXML
    private Button buttonEndGame;
    @FXML
    private AnchorPane layoutObject;

    @FXML
    private void initialize() {
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {}
//        };
//        timer.start();
    }

    public void setGameOnlineController(GameOnlineController gameOnlineController) {
        this.gameOnlineController = gameOnlineController;
    }

    @FXML
    private void buttonAutoPlaceShipsAction(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonStartGameAction(ActionEvent actionEvent) {

    }

    @FXML
    private void buttonEndGameAction(ActionEvent actionEvent) {

    }
}
