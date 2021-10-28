package com.curso.workshopjavafxjdbc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuRegistrationController implements Initializable {

    @FXML
    public void onVBoxSellerAction() {
        System.out.println("onVBoxSellerAction");
    }
    @FXML
    public void onVBoxDepartmentAction() {
        System.out.println("onVBoxDepartmentAction");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
