/*
 * @(#)MainApp.java 1.0 29.12.2016
 */

package ru.solpro.game.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.solpro.game.server.controller.RootLayoutController;
import ru.solpro.game.server.core.ServerLoader;

import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MainAppSrv extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {}

    @Override
    public void start(Stage primaryStage) throws Exception {
        initLayout();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Морской бой. Сервер.");
        this.primaryStage.setScene(new Scene(rootLayout));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ServerLoader.stop();
    }

    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

//            RootLayoutController controller = loader.getController();
//            controller.setMainAppSrv(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
