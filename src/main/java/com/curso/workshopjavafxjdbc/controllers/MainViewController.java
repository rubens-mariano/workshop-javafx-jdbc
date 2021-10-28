package com.curso.workshopjavafxjdbc.controllers;

import com.curso.workshopjavafxjdbc.application.Program;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void onButtonRegistrationAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/MenuRegistration.fxml");
    }
    @FXML
    public void onButtonHelpAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/MenuHelp.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private synchronized void loadView(String absoluteName) {
        try {
            GridPane newGridPane = new FXMLLoader(getClass().getResource(absoluteName)).load();
            Scene mainScene = Program.getMainScene();
            BorderPane borderPane = (BorderPane) mainScene.getRoot();
            borderPane.setCenter(newGridPane);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
