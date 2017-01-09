/*
 * @(#)RootLayoutController.java 1.0 02.01.2017
 */
package ru.solpro.game.server.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import ru.solpro.game.server.MainAppSrv;
import ru.solpro.game.server.core.ServerLoader;

/**
 * Created by danila on 02.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class RootLayoutController {

    @FXML
    private Label serverStatus;
    @FXML
    private Label countBattle;
    @FXML
    private TableView tableListUsers;
    @FXML
    private TextArea textLog;
    @FXML
    private Button buttonViewSetting;
    @FXML
    private Button buttonStartServer;
    @FXML
    private Button buttonStopServer;

    private MainAppSrv mainAppSrv;

    private Thread serverThread;

    public void setMainAppSrv(MainAppSrv mainAppSrv) {
        this.mainAppSrv = mainAppSrv;
    }

    @FXML
    private void buttonViewSettingAction(ActionEvent actionEvent) {}

    @FXML
    private void buttonStartServerAction(ActionEvent actionEvent) {
        if (serverThread == null || !serverThread.isAlive()) {
            serverThread = new Thread(new ServerLoader());
            serverThread.setDaemon(true);
            serverThread.start();
        }
    }

    @FXML
    private void buttonStopServerAction(ActionEvent actionEvent) {
        ServerLoader.stop();
    }
}
