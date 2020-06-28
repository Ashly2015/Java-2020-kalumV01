package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.CarreraTecnica;
import org.rachelbarrios.test.models.Clase;
import org.rachelbarrios.test.models.Horario;
import org.rachelbarrios.test.models.Instructor;
import org.rachelbarrios.test.models.Salon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;

public class VentanaClaseAddUpdateController implements Initializable {
    private App directorEscenas;
    private Clase clase = null;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private ComboBox<Salon> cmbSalon;
    @FXML
    private ComboBox<Horario> cmbHorario;
    @FXML
    private ComboBox<Instructor> cmbInstructor;
    @FXML
    private ComboBox<CarreraTecnica> cmbCarrera;
    @FXML
    private TextField txtCiclo;
    @FXML
    private TextField txtCupoMinimo;
    @FXML
    private TextField txtCupoMaximo;
    @FXML
    private TextField txtCantidadAsignaciones;

    private ObservableList<Salon> salon;
    private ObservableList<Horario> horario;
    private ObservableList<Instructor> instructor;
    private ObservableList<CarreraTecnica> carreraTecnica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salon = FXCollections.observableArrayList((List<Salon>) Conexion.getInstancia().findAll("Salon.findAll"));
        this.cmbSalon.setItems(salon);
        horario = FXCollections.observableArrayList((List<Horario>) Conexion.getInstancia().findAll("Horario.findAll"));
        this.cmbHorario.setItems(horario);
        instructor = FXCollections
                .observableArrayList((List<Instructor>) Conexion.getInstancia().findAll("Instructor.findAll"));
        this.cmbInstructor.setItems(instructor);
        carreraTecnica = FXCollections
                .observableArrayList((List<CarreraTecnica>) Conexion.getInstancia().findAll("CarreraTecnica.findAll"));
        this.cmbCarrera.setItems(carreraTecnica);
    }

    public App getDirectorEscenas() {
        return directorEscenas;
    }

    public void setDirectorEscenas(App directorEscenas) {
        this.directorEscenas = directorEscenas;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
        this.txtDescripcion.setText(clase.getDescripcion());
        
        
        
        this.txtCantidadAsignaciones.setText(String.valueOf(clase.getCantidadAsignaciones()));
        this.txtCiclo.setText(String.valueOf(clase.getCiclo()));
        this.txtCupoMaximo.setText(String.valueOf(clase.getCupoMaximo()));
        this.txtCupoMinimo.setText(String.valueOf(clase.getCupoMinimo()));   
    }

    public void cancelar() {
        this.directorEscenas.mostrarVentanaClase();
    }

    public void guardar() {
        if (txtDescripcion.getText().isEmpty() || txtCiclo.getText().isEmpty() || txtCupoMaximo.getText().isEmpty()
                || txtCupoMinimo.getText().isEmpty() || txtCantidadAsignaciones.getText().isEmpty()
                || cmbCarrera.getSelectionModel().getSelectedItem() == null || cmbSalon.getItems().isEmpty()
                || cmbInstructor.getItems().isEmpty() || cmbHorario.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Clase");
            alert.setHeaderText(null);
            alert.setContentText("Error de entrada de datos, es necesario ingresar la informacion de manera correcta");
            alert.initOwner(null);
            alert.show();
        } else {
            if (clase == null) {
                clase = new Clase();
                clase.setClaseId(UUID.randomUUID().toString());
                clase.setCupoMaximo(Integer.parseInt(txtCupoMaximo.getText()));
                clase.setCupoMinimo(Integer.parseInt(txtCupoMinimo.getText()));
                clase.setDescripcion(txtDescripcion.getText());
                clase.setCiclo(Integer.parseInt(txtCiclo.getText()));
                clase.setCantidadAsignaciones(Integer.parseInt(txtCantidadAsignaciones.getText()));
                clase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                clase.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                clase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                clase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());

                Conexion.getInstancia().agregar(clase);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Clase");
                alert.setHeaderText(null);
                alert.setContentText("Registro almacenado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaClase();

            } else {

                clase.setCupoMaximo(Integer.parseInt(txtCupoMaximo.getText()));
                clase.setCupoMinimo(Integer.parseInt(txtCupoMinimo.getText()));
                clase.setDescripcion(txtDescripcion.getText());
                clase.setCiclo(Integer.parseInt(txtCiclo.getText()));
                clase.setCantidadAsignaciones(Integer.parseInt(txtCantidadAsignaciones.getText()));
                clase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                clase.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                clase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                clase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());

                Conexion.getInstancia().modificar(clase);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Clase");
                alert.setHeaderText(null);
                alert.setContentText("Registro actualizado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaClase();
            }
        }
    }

}