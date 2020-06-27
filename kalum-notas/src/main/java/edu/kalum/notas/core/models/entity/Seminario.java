package edu.kalum.notas.core.models.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="seminario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seminario implements Serializable{
    @Id
    @Column(name = "seminario_id")
    private String seminarioId;
    @NotEmpty(message = "Debe ingresar el nombre")
    @Column(name = "nombre_seminario")
    private String nombreSeminario;

    @Column(name="fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(mappedBy = "seminario", fetch = FetchType.LAZY)
    private List<DetalleActividad> detalleActividades;


}
