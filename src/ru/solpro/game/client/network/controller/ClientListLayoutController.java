/*
 * @(#)ClientListLayoutController.java 1.0 31.12.2016
 */

package ru.solpro.game.client.network.controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.core.packet.AuthenticationPacket;
import ru.solpro.game.client.network.core.packet.LogoutPacket;
import ru.solpro.game.client.network.model.Player;
import ru.solpro.game.client.network.model.StatusPlayer;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientListLayoutController {

    private ObservableList<Player> players = FXCollections.observableArrayList();

    public ObservableList<Player> getPlayers() {
        return players;
    }

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
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> nicknamePlayerColumn;
    @FXML
    private TableColumn<Player, StatusPlayer> statusPlayerColumn;

    public ClientListLayoutController() {}

    @FXML
    private void initialize() {
        ClientLoader.setClientListLayoutController(this);

        nicknamePlayerColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        statusPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("statusPlayer"));
        playerTable.setItems(players);

        buttonConnect.setDisable(false);
        buttonDisconnect.setDisable(true);
        buttonConnectGame.setDisable(true);
        buttonNewGame.setDisable(true);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paintComponent();
            }
        };
        timer.start();
    }

    private void paintComponent() {
        playerTable.setItems(players);
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

    public TableView getPlayerTable() {
        return playerTable;
    }

    public TableColumn getNicknamePlayerColumn() {
        return nicknamePlayerColumn;
    }

    public TableColumn getStatusPlayerColumn() {
        return statusPlayerColumn;
    }
}
