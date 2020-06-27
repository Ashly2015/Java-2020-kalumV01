package edu.kalum.notas.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<DetalleNota> detalleNotas;

}
