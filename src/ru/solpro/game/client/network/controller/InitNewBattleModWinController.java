/*
 * @(#)InitNewBattleModWinController.java 1.0 18.01.2017
 */
package ru.solpro.game.client.network.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.core.packet.ConfirmPacket;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class InitNewBattleModWinController {

    @FXML
    private Button Accept;
    @FXML
    private Button Reject;
    @FXML
    private Label nickname;
    private String nickname2;

    public InitNewBattleModWinController() {}

    public void setNickname(String nickname) {
        this.nickname.setText(nickname);
    }

    public void setNickname2(String nickname2) {
        this.nickname2 = nickname2;
    }

    @FXML
    private void AcceptAction(ActionEvent actionEvent) {
        //TODO: отправить пакет с согласием на бой
        // и закрыть окно
        ClientLoader.sendPacket(new ConfirmPacket(true, nickname.getText(), nickname2));
    }

    @FXML
    private void RejectAction(ActionEvent actionEvent) {
        //TODO: отправить пакет с несогласием на бой
        // и закрыть окно
        ClientLoader.sendPacket(new ConfirmPacket(false, nickname.getText(), nickname2));
    }
}
