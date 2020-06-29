package edu.kalum.notas.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="clase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clase implements Serializable {
    @Id
    @Column(name = "clase_id")
    private String claseId;
    @Column(name = "descripcion")
    private String descripcion;




}
