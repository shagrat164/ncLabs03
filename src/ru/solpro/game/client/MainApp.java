/*
 * @(#)MainApp.java 1.0 22.12.2016
 */

package ru.solpro.game.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.solpro.game.client.controller.RootLayoutController;
import ru.solpro.game.client.network.core.ClientLoader;

/**
 * Морской бой.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Точка входа.
     * @param args    аргументы.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    @Override
    public void init() throws Exception {}

    @Override
    public void start(Stage primaryStage) throws Exception {
        initLayout();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Морской бой");
        this.primaryStage.setScene(new Scene(rootLayout));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ClientLoader.disconnect();
    }

    private void initLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);
    }
}
