package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.Salon;
import javafx.collections.FXCollections;

public class VentanaSalonController implements Initializable {
    private App directorEscenas;
    @FXML
    private TableView<Salon> tblSalones;
    @FXML
    private TableColumn<Salon, String> colNombre;
    @FXML
    private TableColumn<Salon, String> colDescripcion;
    @FXML
    private TableColumn<Salon, Number> colCapacidad;
    private ObservableList<Salon> listaSalones;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaSalones = FXCollections
                .observableArrayList((List<Salon>) Conexion.getInstancia().findAll("Salon.findAll"));
        this.tblSalones.setItems(this.listaSalones);
        this.colNombre.setCellValueFactory(cellNombre -> cellNombre.getValue().nombreSalon());
        this.colDescripcion.setCellValueFactory(cellDescripcion -> cellDescripcion.getValue().descripcion());
        this.colCapacidad.setCellValueFactory(cellCapacidad -> cellCapacidad.getValue().capacidad());

    }

    public void mostrarVentanaPrincipal() {
        this.directorEscenas.mostrarVentanaPrincipal();
    }

    public App getDirectorEscenas() {
        return directorEscenas;
    }

    public void setDirectorEscenas(App directorEscenas) {
        this.directorEscenas = directorEscenas;
    }

    public void mostrarVentanaSalonAddUpdate(){
        this.directorEscenas.mostrarVentanaSalonAddUpdate();
    }

    public void modificar(){
        if(this.tblSalones.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Salon");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();

        }else{
            Salon salon =this.tblSalones.getSelectionModel().getSelectedItem();
            this.directorEscenas.mostrarVentanaSalonAddUpdate(salon);
        }
    }

}