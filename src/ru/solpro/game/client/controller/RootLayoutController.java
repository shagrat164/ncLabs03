/*
 * @(#)RootLayoutController.java 1.0 31.12.2016
 */
package ru.solpro.game.client.controller;

import javafx.event.ActionEvent;
import ru.solpro.game.client.MainApp;
import ru.solpro.game.client.local.controller.GameOfflineController;
import ru.solpro.game.client.network.controller.ClientListController;

/**
 * Created by danila on 31.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class RootLayoutController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void buttonOfflineGameAction(ActionEvent actionEvent) {
        mainApp.getPrimaryStage().setScene(new GameOfflineController().createScene());
    }

    public void buttonOnlineGameAction(ActionEvent actionEvent) {
        mainApp.getPrimaryStage().setScene(new ClientListController().createScene());
    }
}
