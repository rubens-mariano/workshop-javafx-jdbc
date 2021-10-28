package com.curso.workshopjavafxjdbc.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane borderPane = new FXMLLoader(getClass().getResource("/com/curso/workshopjavafxjdbc/gui/MainView.fxml")).load();

            mainScene = new Scene(borderPane);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sample JavaFX application");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }
}
