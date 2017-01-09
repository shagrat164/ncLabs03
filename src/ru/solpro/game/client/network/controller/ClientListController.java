/*
 * @(#)ClientListController.java 1.0 02.01.2017
 */
package ru.solpro.game.client.network.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by danila on 02.01.2017.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientListController {

    public Scene createScene() {
        Scene result = new Scene(initLayout());
        return result;
    }

    private AnchorPane initLayout() {
        AnchorPane result = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ClientListLayout.fxml"));
        try {
            result = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
