/*
 * @(#)RootLayoutController.java 1.0 02.01.2017
 */

package ru.solpro.game.server.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Callback;
import ru.solpro.game.server.MainAppSrv;
import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.model.Player;
import ru.solpro.game.server.model.StatusPlayer;

/**
 * Created by danila on 02.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class RootLayoutController {

    private ObservableList<Player> players = FXCollections.observableArrayList();

    @FXML
    private Label serverStatus;
    @FXML
    private Label countBattle;
    @FXML
    private TextArea textLog;
    @FXML
    private Button buttonViewSetting;
    @FXML
    private Button buttonStartServer;
    @FXML
    private Button buttonStopServer;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> nicknamePlayerColumn;
    @FXML
    private TableColumn<Player, StatusPlayer> statusPlayerColumn;

//    private MainAppSrv mainAppSrv;

    private Thread serverThread;

    public RootLayoutController() {}

    @FXML
    private void initialize() {
        nicknamePlayerColumn.setCellValueFactory(cellData -> cellData.getValue().nicknameProperty());
        statusPlayerColumn.setCellValueFactory(cellData -> cellData.getValue().statusPlayerProperty());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paintComponent();
            }
        };
        timer.start();
    }

//    public void setMainAppSrv(MainAppSrv mainAppSrv) {
//        this.mainAppSrv = mainAppSrv;
//    }

    @FXML
    private void buttonViewSettingAction(ActionEvent actionEvent) {
        //TODO: отображение окна настроек сервера
    }

    @FXML
    private void buttonStartServerAction(ActionEvent actionEvent) {
        if (serverThread == null || !serverThread.isAlive()) {
            ServerLoader.setRootLayoutController(this);
            serverThread = new Thread(new ServerLoader());
            serverThread.setDaemon(true);
            serverThread.start();
        }
    }

    @FXML
    private void buttonStopServerAction(ActionEvent actionEvent) {
        ServerLoader.stop();
    }

    private void paintComponent() {
        playerTable.setItems(players);
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public TextArea getTextLog() {
        return textLog;
    }

    public Label getServerStatus() {
        return serverStatus;
    }

    public Label getCountBattle() {
        return countBattle;
    }

    public TableView<Player> getPlayerTable() {
        return playerTable;
    }

    public TableColumn<Player, StatusPlayer> getStatusPlayerColumn() {
        return statusPlayerColumn;
    }

    public TableColumn<Player, String> getNicknamePlayerColumn() {
        return nicknamePlayerColumn;
    }

    public Button getButtonViewSetting() {
        return buttonViewSetting;
    }

    public Button getButtonStartServer() {
        return buttonStartServer;
    }

    public Button getButtonStopServer() {
        return buttonStopServer;
    }
}
