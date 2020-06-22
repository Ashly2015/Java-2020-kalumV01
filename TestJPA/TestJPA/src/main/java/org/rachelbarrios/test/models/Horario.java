package org.rachelbarrios.test.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "horario")
@NamedQueries(@NamedQuery(name = "Horario.findAll", query = "select h from Horario h"))
public class Horario implements Serializable {
    private final StringProperty horarioId;
    private final ObjectProperty<Date> horarioInicio;
    private final ObjectProperty<Date> horarioFinal;
    private List<Clase> clases;

    public Horario() {
        this.horarioId = new SimpleStringProperty();
        this.horarioInicio = new SimpleObjectProperty<Date>();
        this.horarioFinal = new SimpleObjectProperty<Date>();
    }

    @Id
    @Column(name = "horario_id")
    public String getHorarioId() {
        return this.horarioId.get();
    }

    public void setHorarioId(String horarioId) {
        this.horarioId.set(horarioId);
    }

    public StringProperty horarioId() {
        return this.horarioId;
    }

    @Temporal(value = TemporalType.TIME)
    @Column(name = "horario_inicio")
    public Date getHorarioInicio() {
        return this.horarioInicio.get();
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio.set(horarioInicio);
    }

    public ObjectProperty<Date> horarioInicio() {
        return this.horarioInicio;
    }

    @Temporal(value = TemporalType.TIME)
    @Column(name = "horario_final")
    public Date getHorarioFinal() {
        return this.horarioFinal.get();
    }

    public void setHorarioFinal(Date horarioFinal) {
        this.horarioFinal.set(horarioFinal);
    }

    public ObjectProperty<Date> horarioFinal() {
        return this.horarioFinal;
    }

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "horario", fetch = FetchType.EAGER)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    @Override
    public String toString() {
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return  formatoHora.format(this.getHorarioInicio()) + " "
                + formatoHora.format(this.getHorarioFinal());

    }

}