package com.curso.workshopjavafxjdbc.controllers;

import com.curso.workshopjavafxjdbc.application.Main;
import com.curso.workshopjavafxjdbc.gui.utils.Alerts;
import com.curso.workshopjavafxjdbc.model.services.DepartmentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

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
    @FXML
    public void onVBoxSellerAction() {
        System.out.println("onVBoxSellerAction");
    }
    @FXML
    public void onVBoxDepartmentAction() {
        loadView2("/com/curso/workshopjavafxjdbc/gui/DepartmentList.fxml");
    }
    @FXML
    public void onVBoxAboutAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/About.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private synchronized void loadView(String absoluteName) {
        try {
            StackPane newStackPane = new FXMLLoader(getClass().getResource(absoluteName)).load();
            Scene mainScene = Main.getMainScene();
            BorderPane borderPane = (BorderPane) mainScene.getRoot();
            borderPane.setCenter(newStackPane);
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private synchronized void loadView2(String absoluteName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            StackPane newStackPane = loader.load();
            Scene mainScene = Main.getMainScene();
            BorderPane borderPane = (BorderPane) mainScene.getRoot();
            borderPane.setCenter(newStackPane);

            DepartmentListController controller = loader.getController();
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
