package org.rachelbarrios.test;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

import java.io.IOException;
import java.io.InputStream;

import org.rachelbarrios.test.controllers.VentanaCarreraTecnicaAddUpdateController;
import org.rachelbarrios.test.controllers.VentanaCarreraTecnicaController;
import org.rachelbarrios.test.controllers.VentanaClaseAddUpdateController;
import org.rachelbarrios.test.controllers.VentanaClaseController;
import org.rachelbarrios.test.controllers.VentanaHorarioAddUpdateController;
import org.rachelbarrios.test.controllers.VentanaHorarioController;
import org.rachelbarrios.test.controllers.VentanaInstructorAddUpdateController;
import org.rachelbarrios.test.controllers.VentanaInstructorController;
import org.rachelbarrios.test.controllers.VentanaPrincipalController;
import org.rachelbarrios.test.controllers.VentanaSalonAddUpdateController;
import org.rachelbarrios.test.controllers.VentanaSalonController;

import org.rachelbarrios.test.models.CarreraTecnica;
import org.rachelbarrios.test.models.Clase;
import org.rachelbarrios.test.models.Horario;
import org.rachelbarrios.test.models.Instructor;
import org.rachelbarrios.test.models.Salon;

public class App extends Application {
  private Stage escenarioPrincipal;
  private final String PAQUETE_VISTAS = "/org/rachelbarrios/test/views/";

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage escenarioPrincipal) throws Exception {
    this.escenarioPrincipal = escenarioPrincipal;
    this.escenarioPrincipal.setTitle("kalum v1.0.0");
    mostrarVentanaPrincipal();

    this.escenarioPrincipal.show();

  }

  public void mostrarVentanaPrincipal() {
    try {
      VentanaPrincipalController ventanaPrincipalView = (VentanaPrincipalController) cambiarEscena(
          "VentanaPrincipalView.fxml", 640, 375);
      ventanaPrincipalView.setDirectorEscena(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaClase() {
    try {
      VentanaClaseController ventanaClaseView = (VentanaClaseController) cambiarEscena("ClaseView.fxml", 820, 413);
      ventanaClaseView.setDirectorEscenas(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaSalon() {
    try {
      VentanaSalonController ventanaSalonView = (VentanaSalonController) cambiarEscena("SalonView.fxml", 695, 449);
      ventanaSalonView.setDirectorEscenas(this);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaCarreraTecnica() {
    try {
      VentanaCarreraTecnicaController ventanaCarreraTecnicaView = (VentanaCarreraTecnicaController) cambiarEscena(
          "CarreraTecnicaView.fxml", 580, 439);
      ventanaCarreraTecnicaView.setDirectorEscenas(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaHorarios() {
    try {
      VentanaHorarioController ventanaHorarioView = (VentanaHorarioController) 
      cambiarEscena("HorarioView.fxml", 545,
      434);
      ventanaHorarioView.setDirectorEscenas(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaInstructor() {
    try {
      VentanaInstructorController ventanaInstructorView = (VentanaInstructorController) cambiarEscena(
          "InstructorView.fxml", 769, 448);
      ventanaInstructorView.setDirectorEscenas(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaSalonAddUpdate() {
    try {
      VentanaSalonAddUpdateController ventanaSalonAddUpdate = (VentanaSalonAddUpdateController) cambiarEscena(
          "VentanaSalonAddUpdateView.fxml", 600, 255);
          ventanaSalonAddUpdate.setDirectorEscenas(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaSalonAddUpdate(Salon salon) {
    try {
      VentanaSalonAddUpdateController ventanaSalonAddUpdate = (VentanaSalonAddUpdateController) cambiarEscena(
          "VentanaSalonAddUpdateView.fxml", 600, 255);
          ventanaSalonAddUpdate.setDirectorEscenas(this);
          ventanaSalonAddUpdate.setSalon(salon);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void mostrarVentanaCarreraTecnicaAddUpdate(){
    try {
      VentanaCarreraTecnicaAddUpdateController ventanaCarreraTecnicaAddUpdate = (VentanaCarreraTecnicaAddUpdateController) cambiarEscena(
          "VentanaCarreraTecnicaAddUpdateView.fxml", 600, 185);
          ventanaCarreraTecnicaAddUpdate.setDirectorEscenas(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaCarreraTecnicaAddUpdate(CarreraTecnica carreraTecnica){
    try {
      VentanaCarreraTecnicaAddUpdateController ventanaCarreraTecnicaAddUpdate = (VentanaCarreraTecnicaAddUpdateController) cambiarEscena(
          "VentanaCarreraTecnicaAddUpdateView.fxml", 600, 185);
          ventanaCarreraTecnicaAddUpdate.setDirectorEscenas(this);
          ventanaCarreraTecnicaAddUpdate.setCarreraTecnica(carreraTecnica);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void mostrarVentanaHorarioAddUpdate(){
    try {
      VentanaHorarioAddUpdateController ventanaHorarioAddUpdate = (VentanaHorarioAddUpdateController) cambiarEscena(
          "VentanaHorarioAddUpdateView.fxml", 600, 223);
          ventanaHorarioAddUpdate.setDirectorEscenas(this);
          
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaHorarioAddUpdate(Horario horario){
    try {
      VentanaHorarioAddUpdateController ventanaHorarioAddUpdate = (VentanaHorarioAddUpdateController) cambiarEscena(
          "VentanaHorarioAddUpdateView.fxml", 600, 223);
          ventanaHorarioAddUpdate.setDirectorEscenas(this);
          ventanaHorarioAddUpdate.setHorario(horario);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaInstructorAddUpdate(Instructor instructor){
    try {
      VentanaInstructorAddUpdateController ventanaInstructorAddUpdate = (VentanaInstructorAddUpdateController) cambiarEscena(
          "VentanaInstructorAddUpdateView.fxml", 600, 387);
          ventanaInstructorAddUpdate.setDirectorEscenas(this);
          ventanaInstructorAddUpdate.setInstructor(instructor);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaInstructorAddUpdate(){
    try {
      VentanaInstructorAddUpdateController ventanaInstructorAddUpdate = (VentanaInstructorAddUpdateController) cambiarEscena(
          "VentanaInstructorAddUpdateView.fxml", 600, 387);
          ventanaInstructorAddUpdate.setDirectorEscenas(this);
          
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaClaseAddUpdate(Clase clase){
    try {
      VentanaClaseAddUpdateController ventanaClaseAddUpdate = (VentanaClaseAddUpdateController) cambiarEscena(
          "VentanaClaseAddUpdateView.fxml", 598, 492);
          ventanaClaseAddUpdate.setDirectorEscenas(this);
          ventanaClaseAddUpdate.setClase(clase);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mostrarVentanaClaseAddUpdate(){
    try {
      VentanaClaseAddUpdateController ventanaClaseAddUpdate = (VentanaClaseAddUpdateController) cambiarEscena(
          "VentanaClaseAddUpdateView.fxml", 598, 492);
          ventanaClaseAddUpdate.setDirectorEscenas(this);
          
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
    Initializable resultado = null;
    FXMLLoader cargadorFxml = new FXMLLoader();
    InputStream archivoFXML = App.class.getResourceAsStream(PAQUETE_VISTAS + escena);
    cargadorFxml.setBuilderFactory(new JavaFXBuilderFactory());
    cargadorFxml.setLocation(App.class.getResource(PAQUETE_VISTAS + escena));
    Scene miEscena = new Scene((AnchorPane) cargadorFxml.load(archivoFXML), ancho, alto);
    miEscena.getStylesheets().add("/org/rachelbarrios/recursos/estilo.css");
    this.escenarioPrincipal.setScene(miEscena);
    this.escenarioPrincipal.sizeToScene();
    resultado = (Initializable) cargadorFxml.getController();

    return resultado;
  }
}
