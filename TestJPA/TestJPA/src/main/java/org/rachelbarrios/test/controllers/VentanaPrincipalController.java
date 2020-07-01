package org.rachelbarrios.test.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;

import org.rachelbarrios.test.App;
import org.rachelbarrios.test.db.Conexion;
import org.rachelbarrios.test.models.Clase;
import javafx.collections.FXCollections;

public class VentanaPrincipalController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuC;
    @FXML
    private Menu menuR;
    @FXML
    private Menu menuH;


    private App directorEscena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.styleProperty().set("-fx-background-color: #000000;");
        menuBar.styleProperty().set("-fx-background-color: #00BFFF; -fx-border-color: #F0FFFF ;");
        menuC.styleProperty().set(" -fx-border-color: #F0FFFF ;");
        menuH.styleProperty().set("-fx-border-color: #F0FFFF ;");
        menuR.styleProperty().set(" -fx-border-color: #F0FFFF ;");
        
        
    }

    public void mostrarVentanaSalon() {
        this.directorEscena.mostrarVentanaSalon();
    }

    public void mostrarVentanaCarreraTecnica() {
        this.directorEscena.mostrarVentanaCarreraTecnica();
    }

    public void mostrarVentanaHorario(){
        this.directorEscena.mostrarVentanaHorarios();
    }

    public void mostrarVentanaInstructor(){
        this.directorEscena.mostrarVentanaInstructor();
    }
    public void mostrarVentanaClase(){
        this.directorEscena.mostrarVentanaClase();
    }

    public App getDirectorEscena() {
        return directorEscena;
    }

    public void setDirectorEscena(App directorEscena) {
        this.directorEscena = directorEscena;
    }

}