package edu.kalum.notas.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name="carne")
    private int carne;
    @Column(name = "nombres")
    @NotEmpty
    private String nombres;
    @Column(name = "apellidos")
    @NotEmpty
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

}
