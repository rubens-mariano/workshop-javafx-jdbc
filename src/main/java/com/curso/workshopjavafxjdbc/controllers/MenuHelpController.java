package com.curso.workshopjavafxjdbc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuHelpController implements Initializable {

    @FXML
    public void onVBoxAboutAction() {
        System.out.println("onVBoxAboutAction");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
