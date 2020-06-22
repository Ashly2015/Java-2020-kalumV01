package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.UUID;

import com.google.protobuf.TextFormat.ParseException;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.Horario;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class VentanaHorarioAddUpdateController implements Initializable {

    private App directorEscenas;
    private Horario horario;
    @FXML
    private TextField txtHorarioInicio;
    @FXML
    private TextField txtHorarioFinal;

    DateFormat formato = new SimpleDateFormat("HH:mm:ss");
    
    Date fecha;
    String nuevoFormato;
    
   

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        String horaInicio = txtHorarioInicio.getText();
        String horaFinal = txtHorarioFinal.getText();

        if (txtHorarioInicio.getText().isEmpty() || txtHorarioFinal.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Horario");
            alert.setHeaderText(null);
            alert.setContentText("Error de entrada de datos, es necesario ingresar la informacion de manera correcta");
            alert.initOwner(null);
            alert.show();
        } else {

            if (horario != null) {
               
                try
                {
                    java.util.Date dateInicio;
                    java.util.Date dateFinal;
                    dateInicio = formato.parse(horaInicio);
                    dateFinal = formato.parse(horaFinal);
                    horario.setHorarioInicio(dateInicio);
                    horario.setHorarioFinal(dateFinal);
                }catch( java.text.ParseException e)
                {
            
                    e.printStackTrace();
                }
                
               

                Conexion.getInstancia().modificar(horario);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Horario");
                alert.setHeaderText(null);
                alert.setContentText("Registro actualizado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaHorarios();

            } else {
                
               

                horario = new Horario();
                horario.setHorarioId(UUID.randomUUID().toString());

                try
                {
                    java.util.Date dateInicio;
                    java.util.Date dateFinal;
                    dateInicio = formato.parse(horaInicio);
                    dateFinal = formato.parse(horaFinal);
                    horario.setHorarioInicio(dateInicio);
                    horario.setHorarioFinal(dateFinal);
                }catch(
                java.text.ParseException e)
                {
            
                    e.printStackTrace();
                }

               

                Conexion.getInstancia().agregar(horario);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Horario");
                alert.setHeaderText(null);
                alert.setContentText("Registro almacenado correctamente");
                alert.initOwner(null);
                alert.show();
                this.directorEscenas.mostrarVentanaHorarios();
            }

        }

    }

    public App getDirectorEscenas() {
        return directorEscenas;
    }

    public void setDirectorEscenas(App directorEscenas) {
        this.directorEscenas = directorEscenas;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {

        this.horario = horario;
        this.txtHorarioInicio.setText(formato.format(horario.getHorarioInicio()));
        this.txtHorarioFinal.setText(formato.format(horario.getHorarioFinal()));

    }

    public void cancelar() {
        this.directorEscenas.mostrarVentanaHorarios();
    }

}