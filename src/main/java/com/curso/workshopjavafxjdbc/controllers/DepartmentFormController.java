package com.curso.workshopjavafxjdbc.controllers;

import com.curso.workshopjavafxjdbc.gui.utils.Alerts;
import com.curso.workshopjavafxjdbc.gui.utils.Constraints;
import com.curso.workshopjavafxjdbc.model.entities.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {
    private Department entity;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblError;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    public void setDepartment(Department entity) {
        this.entity = entity;
    }

    @FXML
    public void onBtnSaveAction() {
        Alerts.showAlert("Menssage", "Menssage to Button Action", "onBtnSaveAction Clicked", Alert.AlertType.INFORMATION);
    }

    @FXML
    public void onBtnCancelAction() {
        Alerts.showAlert("Menssage", "Menssage to Button Action", "onBtnCancelAction Clicked", Alert.AlertType.INFORMATION);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void updateFormData() {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
    }
}
