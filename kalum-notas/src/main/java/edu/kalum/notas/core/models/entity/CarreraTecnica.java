package edu.kalum.notas.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.controllers.CarreraTecnicaController;
import edu.kalum.notas.core.models.service.ModuloServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Table(name = "carrera_tecnica")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarreraTecnica implements Serializable {
    @Id
    @Column(name = "carrera_id")
    private String carreraId;
    @NotEmpty(message = "Debe ingresar el nombre")
    @Column(name = "nombre_carrera",unique = true)
    private String nombreCarrera;
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
    private List<Modulo> modulos;

}