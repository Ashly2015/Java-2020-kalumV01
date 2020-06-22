package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.Instructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class VentanaInstructorAddUpdateController implements Initializable {
    private App directorEscenas;
    private Instructor instructor;

    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtComentario;
    @FXML
    private TextField txtEstatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        if (txtApellidos.getText().isEmpty() || txtNombres.getText().isEmpty() || txtDireccion.getText().isEmpty()
                || txtTelefono.getText().isEmpty() || txtEstatus.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Instructor");
            alert.setHeaderText(null);
            alert.setContentText("Error de entrada de datos, es necesario ingresar la informacion de manera correcta");
            alert.initOwner(null);
            alert.show();
        } else {

            if (instructor != null) {
                instructor.setApellidos(txtApellidos.getText());
                instructor.setNombres(txtNombres.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setTelefono(txtTelefono.getText());
                instructor.setComentario(txtComentario.getText());
                instructor.setEstatus(txtEstatus.getText());

                Conexion.getInstancia().modificar(instructor);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Instructor");
                alert.setHeaderText(null);
                alert.setContentText("Registro actualizado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaInstructor();

            } else {
                instructor = new Instructor();
                instructor.setInstructorId(UUID.randomUUID().toString());
                instructor.setApellidos(txtApellidos.getText());
                instructor.setNombres(txtNombres.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setTelefono(txtTelefono.getText());
                instructor.setComentario(txtComentario.getText());
                instructor.setEstatus(txtEstatus.getText());

                Conexion.getInstancia().agregar(instructor);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Instructor");
                alert.setHeaderText(null);
                alert.setContentText("Registro almacenado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaInstructor();
            }

        }

    }

    public void cancelar() {
        this.directorEscenas.mostrarVentanaInstructor();
    }

    public App getDirectorEscenas() {
        return directorEscenas;
    }

    public void setDirectorEscenas(App directorEscenas) {
        this.directorEscenas = directorEscenas;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        this.txtApellidos.setText(instructor.getApellidos());
        this.txtNombres.setText(instructor.getNombres());
        this.txtDireccion.setText(instructor.getDireccion());
        this.txtTelefono.setText(instructor.getTelefono());
        this.txtComentario.setText(instructor.getComentario());
        this.txtEstatus.setText(instructor.getEstatus());

    }

}