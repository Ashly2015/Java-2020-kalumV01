package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.rachelbarrios.test.models.Clase;
import org.rachelbarrios.test.models.Horario;

import javafx.collections.FXCollections;

public class VentanaHorarioController implements Initializable {
    private App directorEscenas;
    @FXML
    private TableView<Horario> tblHorarios;
    @FXML
    private TableColumn<Horario, Date> colHorarioInicio;
    @FXML
    private TableColumn<Horario, Date> colHorarioFinal;
    private ObservableList<Horario> listaHorarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat formato = new SimpleDateFormat("HH:mm:ss");
        this.listaHorarios = FXCollections
                .observableArrayList((List<Horario>) Conexion.getInstancia().findAll("Horario.findAll"));
        this.tblHorarios.setItems(this.listaHorarios);
        this.colHorarioInicio.setCellValueFactory(cellHorarioInicio -> cellHorarioInicio.getValue().horarioInicio());
        this.colHorarioFinal.setCellValueFactory(cellHorarioFinal -> cellHorarioFinal.getValue().horarioFinal());
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

 
   
}