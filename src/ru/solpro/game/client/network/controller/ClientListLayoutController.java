/*
 * @(#)ClienListLayoutController.java 1.0 31.12.2016
 */
package ru.solpro.game.client.network.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ru.solpro.game.client.network.core.Client;

/**
 * Created by danila on 31.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientListLayoutController {

    @FXML
    private Button buttonConnectGame;
    @FXML
    private Button buttonNewGame;
    @FXML
    private TextField serverAddress;
    @FXML
    private TextField portNumber;
    @FXML
    private TextField playerName;
    @FXML
    private Button buttonConnect;
    @FXML
    private Button buttonDisconnect;
    @FXML
    private TableView tableClientList;
    @FXML
    private TableColumn columnUserName;
    @FXML
    private TableColumn columnUserStatus;

    @FXML
    private void connectGameAction(ActionEvent actionEvent) {}

    @FXML
    private void newGameAction(ActionEvent actionEvent) {}

    @FXML
    private void connectAction(ActionEvent actionEvent) {
//        Client client = new Client();
//        client.connect();
//        client.handle();
//        client.disconnect();
    }

    @FXML
    private void disconnectAction(ActionEvent actionEvent) {}
}
