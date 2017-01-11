/*
 * @(#)ClientListLayoutController.java 1.0 31.12.2016
 */

package ru.solpro.game.client.network.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.core.packet.AuthenticationPacket;
import ru.solpro.game.client.network.core.packet.LogoutPacket;

/**
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

    public ClientListLayoutController() {}

    @FXML
    private void initialize() {
        ClientLoader.setClientListLayoutController(this);

        buttonConnect.setDisable(false);
        buttonDisconnect.setDisable(true);
        buttonConnectGame.setDisable(true);
        buttonNewGame.setDisable(true);
    }

    @FXML
    private void connectGameAction(ActionEvent actionEvent) {
        //TODO: присоединиться к существующему бою.
    }

    @FXML
    private void newGameAction(ActionEvent actionEvent) {
        //TODO: создание нового боя.
    }

    @FXML
    private void connectAction(ActionEvent actionEvent) {
        int port = Integer.parseInt(portNumber.getText());
        ClientLoader.connect(serverAddress.getText(), port);
        ClientLoader.sendPacket(new AuthenticationPacket(playerName.getText()));
    }

    @FXML
    private void disconnectAction(ActionEvent actionEvent) {
        ClientLoader.sendPacket(new LogoutPacket(playerName.getText()));
        ClientLoader.disconnect();
    }

    public Button getButtonConnectGame() {
        return buttonConnectGame;
    }

    public Button getButtonNewGame() {
        return buttonNewGame;
    }

    public TextField getServerAddress() {
        return serverAddress;
    }

    public TextField getPortNumber() {
        return portNumber;
    }

    public TextField getPlayerName() {
        return playerName;
    }

    public Button getButtonConnect() {
        return buttonConnect;
    }

    public Button getButtonDisconnect() {
        return buttonDisconnect;
    }

    public TableView getTableClientList() {
        return tableClientList;
    }

    public TableColumn getColumnUserName() {
        return columnUserName;
    }

    public TableColumn getColumnUserStatus() {
        return columnUserStatus;
    }
}
