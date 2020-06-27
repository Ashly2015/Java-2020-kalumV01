package edu.kalum.notas.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
@Entity
@Table(name="detalle_nota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleNota implements Serializable {
    @Id
    @Column(name="detalle_nota_id")
    private String detalleNotaId;
    @Min(0)
    @Column(name = "valor_nota")
    private int valorNota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detalle_actividad_id")
    private DetalleActividad detalleActividad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carne",referencedColumnName = "id")
    private Alumno alumno;

}
