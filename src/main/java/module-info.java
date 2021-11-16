module com.curso.workshopjavafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.curso.workshopjavafxjdbc.application to javafx.fxml;
    exports com.curso.workshopjavafxjdbc.application;
    opens com.curso.workshopjavafxjdbc.controllers to javafx.fxml;
    exports com.curso.workshopjavafxjdbc.controllers;
    exports com.curso.workshopjavafxjdbc.model.services;
    exports com.curso.workshopjavafxjdbc.model.entities;
}