/*
 * @(#)MainApp.java 1.0 22.12.2016
 */

package ru.solpro.game.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.solpro.game.client.network.core.ClientLoader;

/**
 * Морской бой.
 * @author Protsvetov Danila
 * @version 1.0
 */
public class MainApp extends Application {

    private static Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Точка входа.
     * @param args    аргументы.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
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

        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Морской бой");
        MainApp.primaryStage.setScene(new Scene(rootLayout));
        MainApp.primaryStage.setResizable(false);
        MainApp.primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ClientLoader.disconnect();
    }

    private void initLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
    }
}
