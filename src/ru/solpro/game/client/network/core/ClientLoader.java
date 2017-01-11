/*
 * @(#)ClientLoader.java 1.0 09.01.2017
 */

package ru.solpro.game.client.network.core;

import javafx.scene.control.Alert;
import ru.solpro.game.client.network.controller.ClientListLayoutController;
import ru.solpro.game.client.network.core.packet.Packet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class ClientLoader {

    private static ClientListLayoutController clientListLayoutController;
    private static Socket socket;

    /**
     * Подключение к серверу.
     */
    public static void connect(String host, int port) {
        if ((socket == null) || (socket.isClosed()) || (!socket.isBound())) {
            try {
                socket = new Socket(host, port);
                clientListLayoutController.getButtonConnect().setDisable(true);
                clientListLayoutController.getButtonDisconnect().setDisable(false);
                clientListLayoutController.getButtonConnectGame().setDisable(false);
                clientListLayoutController.getButtonNewGame().setDisable(false);
                clientListLayoutController.getPlayerName().setDisable(true);
                clientListLayoutController.getServerAddress().setDisable(true);
                clientListLayoutController.getPortNumber().setDisable(true);
                new ClientHandler(socket).start();
            } catch (ConnectException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Сервер не доступен.");
                alert.setContentText(e.toString());
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Отключение от сервера.
     */
    public static void disconnect() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ((socket == null) || (socket.isClosed()) || (!socket.isBound())) {
            return;
        }
        try {
            socket.close();
            clientListLayoutController.getButtonConnect().setDisable(false);
            clientListLayoutController.getButtonDisconnect().setDisable(true);
            clientListLayoutController.getButtonConnectGame().setDisable(true);
            clientListLayoutController.getButtonNewGame().setDisable(true);
            clientListLayoutController.getPlayerName().setDisable(false);
            clientListLayoutController.getServerAddress().setDisable(false);
            clientListLayoutController.getPortNumber().setDisable(false);
        } catch (SocketException e) {
            /*NOP*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacket(Packet packet) {
        if ((socket == null) || (socket.isClosed()) || (!socket.isBound())) {
            return;
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeShort(packet.getId());
            packet.write(dataOutputStream);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setClientListLayoutController(ClientListLayoutController clientListLayoutController) {
        ClientLoader.clientListLayoutController = clientListLayoutController;
    }
}
