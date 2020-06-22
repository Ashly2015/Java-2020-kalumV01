package org.rachelbarrios.test.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "clase")
@NamedQueries(@NamedQuery(name = "Clase.findAll", query = "select c from Clase c"))
public class Clase implements Serializable {
    private final StringProperty claseId;
    private final StringProperty descripcion;
    private final IntegerProperty ciclo;
    private Salon salon;
    private Horario horario;
    private Instructor instructor;
    private CarreraTecnica carreraTecnica;
    private final IntegerProperty cupoMinimo;
    private final IntegerProperty cupoMaximo;
    private final IntegerProperty cantidadAsignaciones;

    public Clase() {
        this.claseId = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        this.ciclo = new SimpleIntegerProperty();
        this.cupoMinimo = new SimpleIntegerProperty();
        this.cupoMaximo = new SimpleIntegerProperty();
        this.cantidadAsignaciones = new SimpleIntegerProperty();
    }

    @Id
    @Column(name = "clase_id")
    public String getClaseId() {
        return this.claseId.get();
    }

    public void setClaseId(String claseId) {
        this.claseId.set(claseId);
    }

    public StringProperty claseId() {
        return this.claseId;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return this.descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcion() {
        return this.descripcion;
    }

    @Column(name = "ciclo")
    public int getCiclo() {
        return this.ciclo.get();
    }

    public void setCiclo(int ciclo) {
        this.ciclo.set(ciclo);
    }

    public IntegerProperty ciclo() {
        return this.ciclo;
    }

    @Column(name = "cupo_minimo")
    public int getCupoMinimo() {
        return this.cupoMinimo.get();
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo.set(cupoMinimo);
    }

    public IntegerProperty cupoMinimo() {
        return this.cupoMinimo;
    }

    @Column(name = "cupo_maximo")
    public int getCupoMaximo() {
        return this.cupoMaximo.get();
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo.set(cupoMaximo);
    }

    public IntegerProperty cupoMaximo() {
        return this.cupoMaximo;
    }

    @Column(name = "cantidad_asignaciones")
    public int getCantidadAsignaciones() {
        return this.cantidadAsignaciones.get();
    }

    public void setCantidadAsignaciones(int cantidadAsignaciones) {
        this.cantidadAsignaciones.set(cantidadAsignaciones);
    }

    public IntegerProperty cantidadAsignaciones() {
        return this.cantidadAsignaciones;
    }

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salon_id", referencedColumnName = "salon_id")
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "horario_id", referencedColumnName = "horario_id")
    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id")
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrera_id", referencedColumnName = "carrera_id")
    public CarreraTecnica getCarreraTecnica() {
        return carreraTecnica;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
    }

}