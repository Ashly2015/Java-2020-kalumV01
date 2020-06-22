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


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;

public class VentanaClaseController implements Initializable {
    @FXML
    private TableView<Clase> tblClases;
    @FXML
    private TableColumn<Clase,String> colDescripcion;
    @FXML
    private TableColumn<Clase,String> colCarrera;
    @FXML
    private TableColumn<Clase,Date> colHorario;
    @FXML
    private TableColumn<Clase,String> colInstructor;
    @FXML
    private TableColumn<Clase,String> colSalon;
    @FXML
    private TableColumn<Clase,Number> colCiclo;
    @FXML
    private TableColumn<Clase,Number> colCupoMaximo;
    @FXML
    private TableColumn<Clase,Number> colCantidadAsignaciones;

    private App directorEscenas;
  
    private ObservableList<Clase> listaClases;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        this.listaClases = FXCollections
                .observableArrayList((List<Clase>) Conexion.getInstancia().findAll("Clase.findAll"));
        this.tblClases.setItems(this.listaClases);
        this.colDescripcion.setCellValueFactory(cellDescripcion->cellDescripcion.getValue().descripcion());
        this.colCarrera.setCellValueFactory(cellCarrera->cellCarrera.getValue().getCarreraTecnica().nombreCarrera());

        //this.colHorario.setCellValueFactory(cellHorario -> new ReadOnlyStringWrapper(formatoHora.format(cellHorario.getValue().getHorario().getHorarioInicio())  + "-" + formatoHora.format(cellHorario.getValue().getHorario().getHorarioFinal())));
        
      
        this.colInstructor.setCellValueFactory(cellInstructor-> new ReadOnlyStringWrapper(cellInstructor.getValue().getInstructor().getApellidos()+ " "+ cellInstructor.getValue().getInstructor().getNombres()) );
        this.colSalon.setCellValueFactory(cellSalon->cellSalon.getValue().getSalon().nombreSalon());
        this.colCiclo.setCellValueFactory(cellCiclo->cellCiclo.getValue().ciclo());
        this.colCupoMaximo.setCellValueFactory(cellCupoMaximo->cellCupoMaximo.getValue().cupoMaximo());
        this.colCantidadAsignaciones.setCellValueFactory(cellCantidadAsignacones->cellCantidadAsignacones.getValue().cantidadAsignaciones());

    }
   

    public void mostrarVentanaPrincipal() {
        this.directorEscenas.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaClaseAddUpdate(){
        this.directorEscenas.mostrarVentanaClaseAddUpdate();
    }

    public App getDirectorEscenas() {
        return directorEscenas;
    }

    public void setDirectorEscenas(App directorEscenas) {
        this.directorEscenas = directorEscenas;
    }

    public void modificar(){
        if(this.tblClases.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Clase");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();

        }else{
            Clase clase =this.tblClases.getSelectionModel().getSelectedItem();
            this.directorEscenas.mostrarVentanaClaseAddUpdate(clase);
        }
    }

}