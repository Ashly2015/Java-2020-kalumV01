package edu.kalum.notas.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="asignacion_alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionAlumno implements Serializable {
    @Id
    @Column(name = "asignacion_id")
    private String asignacionId;
    @Column(name = "fecha_asignacion")
    private Date fechaAsignacion;
    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clase_id")
    private Clase clase;





}
