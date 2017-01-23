/*
 * @(#)ClientListLayoutController.java 1.0 31.12.2016
 */

package ru.solpro.game.client.network.controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.solpro.game.client.MainApp;
import ru.solpro.game.client.local.controller.GameOfflineController;
import ru.solpro.game.client.network.core.ClientLoader;
import ru.solpro.game.client.network.core.packet.AuthenticationPacket;
import ru.solpro.game.client.network.core.packet.CreateNewBattlePacket;
import ru.solpro.game.client.network.core.packet.LogoutPacket;
import ru.solpro.game.client.network.model.Player;
import ru.solpro.game.client.network.model.StatusPlayer;

import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientListLayoutController {

    private ObservableList<Player> players = FXCollections.observableArrayList();
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

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String nickname) {
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) {
                return player;
            }
        }
        return null;
    }

    public Player getPlayer(int userId) {
        for (Player player : players) {
            if (player.getId() == userId) {
                return player;
            }
        }
        return null;
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

    @FXML
    private void initialize() {
        ClientLoader.setClientListLayoutController(this);

        nicknamePlayerColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        statusPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("statusPlayer"));
        playerTable.setItems(players);

        buttonConnect.setDisable(false);
        buttonDisconnect.setDisable(true);
        buttonNewGame.setDisable(true);

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                paintComponent();
//            }
//        };
//        timer.start();
    }

    @FXML
    private void newGameAction(ActionEvent actionEvent) {
        //TODO: запрос пользователю на бой
        /* получить id выбранного свободного пользователя
         * отправить запрос выбранному пользователю на бой через сервер
         * если пользователь согласился - начать новый бой.
         * иначе ничего не делать
         */

        // получение выбранной строки в таблице.
        // т.к. множественный выбор в таблице запрещён
        // всегда будет один элемент, поэтому get(0)
        Player selectedPlayer = playerTable.getSelectionModel().getSelectedItems().get(0);
        if (selectedPlayer != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            MainApp.getPrimaryStage().setScene(new GameOfflineController().createScene());
        }
    }

    @FXML
    private void connectAction(ActionEvent actionEvent) {
        int port = Integer.parseInt(portNumber.getText());
        ClientLoader.connect(serverAddress.getText(), port);
        ClientLoader.sendPacket(new AuthenticationPacket(playerName.getText()));
    }

    @FXML
    private void disconnectAction(ActionEvent actionEvent) {
        players.clear();
        ClientLoader.sendPacket(new LogoutPacket());
        ClientLoader.disconnect();
    }

    public void initNewBattleWinShow(String nickname, String nickname2) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/InitNewBattleModWin.fxml"));
            GridPane root = (GridPane) loader.load();

            InitNewBattleModWinController controller = loader.getController();
            controller.setNickname(nickname);
            controller.setNickname2(nickname2);

            stage.setTitle("Запрос на бой");
            stage.setMinWidth(300);
            stage.setMinHeight(100);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
