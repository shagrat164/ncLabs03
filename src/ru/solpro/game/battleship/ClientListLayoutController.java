/*
 * @(#)ClienListLayoutController.java 1.0 31.12.2016
 */
package ru.solpro.game.battleship;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by danila on 31.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientListLayoutController {
    @FXML
    public Button buttonConnectGame;
    @FXML
    public Button buttonNewGame;
    @FXML
    public TextField serverAddress;
    @FXML
    public TextField portNumber;
    @FXML
    public TextField playerName;
    @FXML
    public Button buttonConnect;
    @FXML
    public Button buttonDisconnect;
}
