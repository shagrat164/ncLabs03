/*
 * @(#)SettingModalWindowController.java 1.0 11.01.2017
 */

package ru.solpro.game.server.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.solpro.game.server.core.ServerLoader;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class SettingModalWindowController {

    @FXML
    private TextField portNumber;
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;

    public SettingModalWindowController() {}

    @FXML
    private void initialize() {
        portNumber.setText(String.valueOf(ServerLoader.getPort()));
    }

    @FXML
    private void buttonOkAction(ActionEvent actionEvent) {
        ServerLoader.setPort(Integer.parseInt(portNumber.getText()));

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buttonCancelAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
