/*
 * @(#)RootLayoutController.java 1.0 27.12.2016
 */

package ru.solpro.game.battleship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by Администратор on 27.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class RootLayoutController {

    @FXML
    public void buttonNewGameAction(ActionEvent actionEvent) {
        System.out.println("new game");
        MainApp.battle.getMyGame().start();
    }

    @FXML
    public void buttonExitAction(ActionEvent actionEvent) {
        System.out.println("exit");
        System.exit(0);
    }
}
