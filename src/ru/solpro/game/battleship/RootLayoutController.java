/*
 * @(#)RootLayoutController.java 1.0 31.12.2016
 */
package ru.solpro.game.battleship;

import javafx.event.ActionEvent;

/**
 * Created by danila on 31.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class RootLayoutController {
    public void menuCloseAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void menuAboutAction(ActionEvent actionEvent) {
        System.out.println("AboutWindows.");
    }
}
