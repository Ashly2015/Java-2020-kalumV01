package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.CarreraTecnica;
import org.rachelbarrios.test.models.Clase;
import javafx.collections.FXCollections;
public class VentanaCarreraTecnicaController implements Initializable{
private App directorEscenas;
@FXML
private TableView<CarreraTecnica> tblCarrerasTecnicas;
@FXML
private TableColumn<CarreraTecnica, String> colNombre;
private ObservableList<CarreraTecnica> listaCarreraTecnica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaCarreraTecnica = FXCollections
.observableArrayList((List<CarreraTecnica>) Conexion.getInstancia().findAll("CarreraTecnica.findAll"));
this.tblCarrerasTecnicas.setItems(this.listaCarreraTecnica);
this.colNombre.setCellValueFactory(cellNombre -> cellNombre.getValue().nombreCarrera());

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
    public void mostrarVentanaCarreraTecnicaAddUpdate(){
        this.directorEscenas.mostrarVentanaCarreraTecnicaAddUpdate();
    }

    public void modificar(){
        if(this.tblCarrerasTecnicas.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Carrera Tecnica");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();

        }else{
            CarreraTecnica carreraTecnica =this.tblCarrerasTecnicas.getSelectionModel().getSelectedItem();
            this.directorEscenas.mostrarVentanaCarreraTecnicaAddUpdate(carreraTecnica);
        }
    }

    public void eliminar() {
        try{
        if (this.tblCarrerasTecnicas.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Carrera Tecnica");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();
        } else {
            CarreraTecnica carreraTecnica = this.tblCarrerasTecnicas.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Carrera Tecnica");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(carreraTecnica);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Carrera Tecnica");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscenas.mostrarVentanaCarreraTecnica();
            } else {
                this.directorEscenas.mostrarVentanaCarreraTecnica();
            }
        }
    }catch (Exception e) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setTitle("Carrera tecnica");
        alert1.setHeaderText(null);
        alert1.setContentText("La carrera tecnica esta asignado a una clase y se necesita que se elimine esa asignacion antes de eliminar la carrera tecnica");
        alert1.initOwner(null);
        alert1.show();
        this.directorEscenas.mostrarVentanaCarreraTecnica();
    }
    }

}