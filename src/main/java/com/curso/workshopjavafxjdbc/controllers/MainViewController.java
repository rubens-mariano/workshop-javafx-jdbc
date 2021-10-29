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
import java.util.function.Consumer;

public class MainViewController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void onButtonRegistrationAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/MenuRegistration.fxml", x -> {});
    }
    @FXML
    public void onButtonHelpAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/MenuHelp.fxml", x -> {});
    }
    @FXML
    public void onVBoxSellerAction() {
        System.out.println("onVBoxSellerAction");
    }
    @FXML
    public void onVBoxDepartmentAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });
    }
    @FXML
    public void onVBoxAboutAction() {
        loadView("/com/curso/workshopjavafxjdbc/gui/About.fxml", x -> {});
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            StackPane newStackPane = loader.load();
            Scene mainScene = Main.getMainScene();
            BorderPane borderPane = (BorderPane) mainScene.getRoot();
            borderPane.setCenter(newStackPane);

            T controller = loader.getController();
            initializingAction.accept(controller);
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
