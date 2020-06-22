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
@Table(name = "instructor")
@NamedQueries(@NamedQuery(name = "Instructor.findAll", query = "select i from Instructor i"))
public class Instructor implements Serializable {
    private final StringProperty instructorId;
    private final StringProperty apellidos;
    private final StringProperty nombres;
    private final StringProperty direccion;
    private final StringProperty telefono;
    private final StringProperty comentario;
    private final StringProperty estatus;
    private final StringProperty foto;
    private List<Clase> clases;

    public Instructor() {
        this.instructorId = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.comentario = new SimpleStringProperty();
        this.estatus = new SimpleStringProperty();
        this.foto = new SimpleStringProperty();
    }

    @Id
    @Column(name = "instructor_id")

    public String getInstructorId() {
        return this.instructorId.get();
    }

    public void setInstructorId(String instructorId) {
        this.instructorId.set(instructorId);
    }

    public StringProperty instructorId() {
        return this.instructorId;
    }

    @Column(name = "apellidos")
    public String getApellidos() {
        return this.apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidos() {
        return this.apellidos;
    }

    @Column(name = "nombres")
    public String getNombres() {
        return this.nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public StringProperty nombres() {
        return this.nombres;
    }

    @Column(name = "direccion")
    public String getDireccion() {
        return this.direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccion() {
        return this.direccion;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return this.telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty telefono() {
        return this.telefono;
    }

    @Column(name = "comentario")
    public String getComentario() {
        return this.comentario.get();
    }

    public void setComentario(String comentario) {
        this.comentario.set(comentario);
    }

    public StringProperty comentario() {
        return this.comentario;
    }

    @Column(name = "estatus")
    public String getEstatus() {
        return this.estatus.get();
    }

    public void setEstatus(String estatus) {
        this.estatus.set(estatus);
    }

    public StringProperty estatus() {
        return this.estatus;
    }

    @Column(name = "foto")
    public String getFoto() {
        return this.foto.get();
    }

    public void setFoto(String foto) {
        this.foto.set(foto);
    }

    public StringProperty foto() {
        return this.foto;
    }

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public String toString(){
        return this.getNombres()+" "+this.getApellidos();
    }

}