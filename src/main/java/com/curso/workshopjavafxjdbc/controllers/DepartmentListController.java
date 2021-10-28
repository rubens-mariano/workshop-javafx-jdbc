package com.curso.workshopjavafxjdbc.controllers;

import com.curso.workshopjavafxjdbc.application.Main;
import com.curso.workshopjavafxjdbc.model.entities.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentListController implements Initializable {

    @FXML
    private TableView<Department> tableViewDepartment;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    @FXML
    private Button btnNew;

    @FXML
    public void onButtonBtnNewAction() {
        System.out.println("onButtonBtnNewAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //Stage stage = (Stage) Main.getMainScene().getWindow();
        //tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }
}
