package org.rachelbarrios.test.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "carrera_tecnica")
@NamedQueries(@NamedQuery(name = "CarreraTecnica.findAll", query = "select ct from CarreraTecnica ct"))
public class CarreraTecnica implements Serializable {

    private final StringProperty carreraId;
    private final StringProperty nombreCarrera;
    private List<Clase> clases;

    public CarreraTecnica() {
        this.carreraId = new SimpleStringProperty();
        this.nombreCarrera = new SimpleStringProperty();
    }

    @Id
    @Column(name = "carrera_id")
    public String getCarreraId() {
        return this.carreraId.get();
    }

    public void setCarreraId(String carreraId) {
        this.carreraId.set(carreraId);
    }

    public StringProperty carreraId() {
        return this.carreraId;
    }

    @Column(name = "nombre_carrera")
    public String getNombreCarrera() {
        return this.nombreCarrera.get();
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera.set(nombreCarrera);
    }

    public StringProperty nombreCarrera() {
        return this.nombreCarrera;
    }

    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.EAGER)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
    public String toString(){
        return this.getNombreCarrera();
    }

    private static final long serialVersionUID = 1L;

}
