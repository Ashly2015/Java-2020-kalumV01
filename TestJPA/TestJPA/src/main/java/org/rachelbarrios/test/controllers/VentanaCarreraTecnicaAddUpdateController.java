package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.CarreraTecnica;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class VentanaCarreraTecnicaAddUpdateController implements Initializable{
    private App directorEscenas;
    private CarreraTecnica carreraTecnica;
    @FXML private TextField txtCarreraTecnica;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       

    }
    public void guardar() {
        if (txtCarreraTecnica.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Carrera Tecnica");
            alert.setHeaderText(null);
            alert.setContentText("Error de entrada de datos, es necesario ingresar la informacion de manera correcta");
            alert.initOwner(null);
            alert.show();
        } else {

            if (carreraTecnica != null) {
                carreraTecnica.setNombreCarrera(txtCarreraTecnica.getText());
                Conexion.getInstancia().modificar(carreraTecnica);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Carrera Tecnica");
                alert.setHeaderText(null);
                alert.setContentText("Registro actualizado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaCarreraTecnica();

            } else {
                carreraTecnica = new CarreraTecnica();
                carreraTecnica.setCarreraId(UUID.randomUUID().toString());
                carreraTecnica.setNombreCarrera(txtCarreraTecnica.getText());
               

                Conexion.getInstancia().agregar(carreraTecnica);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Carrera Tecnica");
                alert.setHeaderText(null);
                alert.setContentText("Registro almacenado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaCarreraTecnica();
            }

        }

    }
    public void cancelar() {
        this.directorEscenas.mostrarVentanaCarreraTecnica();
    }

    public App getDirectorEscenas() {
        return directorEscenas;
    }

    public void setDirectorEscenas(App directorEscenas) {
        this.directorEscenas = directorEscenas;
    }

    public CarreraTecnica getCarreraTecnica() {
        return carreraTecnica;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
        this.txtCarreraTecnica.setText(carreraTecnica.getNombreCarrera());
    }
    
}