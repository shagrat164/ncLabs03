/*
 * @(#)RootLayoutController.java 1.0 02.01.2017
 */

package ru.solpro.game.server.controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import ru.solpro.game.server.MainAppSrv;
import ru.solpro.game.server.core.ServerLoader;
import ru.solpro.game.server.model.Player;

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
    private TableView<Player> tableListUsers;
    @FXML
    private TableColumn<Player, String> columnStatus;
    @FXML
    private TableColumn<Player, String> columnPlayer;

    private MainAppSrv mainAppSrv;

    private Thread serverThread;

    public RootLayoutController() {}

    @FXML
    private void initialize() {}

    public void setMainAppSrv(MainAppSrv mainAppSrv) {
        this.mainAppSrv = mainAppSrv;
    }

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

    public TableView getTableListUsers() {
        return tableListUsers;
    }

    public TableColumn getColumnStatus() {
        return columnStatus;
    }

    public TableColumn getColumnPlayer() {
        return columnPlayer;
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
