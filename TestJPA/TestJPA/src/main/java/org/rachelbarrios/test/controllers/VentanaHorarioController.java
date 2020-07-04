package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.rachelbarrios.test.models.Clase;
import org.rachelbarrios.test.models.Horario;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;

public class VentanaHorarioController implements Initializable {
    private App directorEscenas;
    @FXML
    private TableView<Horario> tblHorarios;
    @FXML
    private TableColumn<Horario, String> colHorarioInicio;
    @FXML
    private TableColumn<Horario, String> colHorarioFinal;
    private ObservableList<Horario> listaHorarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat formato = new SimpleDateFormat("HH:mm:ss");
        this.listaHorarios = FXCollections.observableArrayList((List<Horario>) Conexion.getInstancia().findAll("Horario.findAll"));
        this.tblHorarios.setItems(this.listaHorarios);
       

        this.colHorarioInicio.setCellValueFactory(cellHorarioInicio-> new ReadOnlyStringWrapper
        (formato.format(cellHorarioInicio.getValue().getHorarioInicio())));
        this.colHorarioFinal.setCellValueFactory(cellHorarioFinal-> new ReadOnlyStringWrapper(formato.format(cellHorarioFinal.getValue().getHorarioFinal())));

       
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

    public void mostrarVentanaHorarioAddUpdate(){
        this.directorEscenas.mostrarVentanaHorarioAddUpdate();
    }

    public void modificar(){
        if(this.tblHorarios.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Horario");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();

        }else{
            Horario horario =this.tblHorarios.getSelectionModel().getSelectedItem();
            this.directorEscenas.mostrarVentanaHorarioAddUpdate(horario);
        }
    }

    public void eliminar() {
        try{
        if (this.tblHorarios.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Horario");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();
        } else {
            Horario horario = this.tblHorarios.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Horario");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el registro?");
            alert.initOwner(null);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Conexion.getInstancia().eliminar(horario);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Horario");
                alert1.setHeaderText(null);
                alert1.setContentText("Registro eliminado correctamente");
                alert1.initOwner(null);
                alert1.show();
                this.directorEscenas.mostrarVentanaHorarios();
            } else {
                this.directorEscenas.mostrarVentanaHorarios();
            }
        }
    }catch (Exception e) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setTitle("Horario");
        alert1.setHeaderText(null);
        alert1.setContentText("El horario esta asignado a una clase y se necesita que se elimine esa asignacion antes de eliminar el horario");
        alert1.initOwner(null);
        alert1.show();
        this.directorEscenas.mostrarVentanaHorarios();
    }
    }

 
   
}