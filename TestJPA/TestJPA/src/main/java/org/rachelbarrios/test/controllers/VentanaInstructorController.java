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
import org.rachelbarrios.test.models.Clase;
import org.rachelbarrios.test.models.Instructor;

import javafx.collections.FXCollections;
public class VentanaInstructorController implements Initializable{
    private App directorEscenas;
    @FXML
    private TableView<Instructor> tblInstructores;
    @FXML
    private TableColumn<Instructor, String> colApellidos;
    @FXML
    private TableColumn<Instructor, String> colNombres;
    @FXML
    private TableColumn<Instructor, String> colDireccion;
    @FXML
    private TableColumn<Instructor, String> colTelefono;
    @FXML
    private TableColumn<Instructor, String> colComentario;
    @FXML
    private TableColumn<Instructor, String> colEstatus;
    private ObservableList<Instructor> listaInstructores;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaInstructores= FXCollections
        .observableArrayList((List<Instructor>) Conexion.getInstancia().findAll("Instructor.findAll"));
this.tblInstructores.setItems(this.listaInstructores);
this.colApellidos.setCellValueFactory(cellApellidos -> cellApellidos.getValue().apellidos());
this.colNombres.setCellValueFactory(cellNombres -> cellNombres.getValue().nombres());
this.colDireccion.setCellValueFactory(cellDireccion -> cellDireccion.getValue().direccion());
this.colTelefono.setCellValueFactory(cellTelefono->cellTelefono.getValue().telefono());
this.colComentario.setCellValueFactory(cellComentario -> cellComentario.getValue().comentario());
this.colEstatus.setCellValueFactory(cellEstatus -> cellEstatus.getValue().estatus());

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

    public void mostrarVentanaInstructorAddUpdate(){
        this.directorEscenas.mostrarVentanaInstructorAddUpdate();
    }

    public void modificar(){
        if(this.tblInstructores.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Instructor");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un elemento");
            alert.initOwner(null);
            alert.show();

        }else{
            Instructor instructor =this.tblInstructores.getSelectionModel().getSelectedItem();
            this.directorEscenas.mostrarVentanaInstructorAddUpdate(instructor);
        }
    }
}