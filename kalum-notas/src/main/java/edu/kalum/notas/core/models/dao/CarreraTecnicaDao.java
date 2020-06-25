package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraTecnicaDao extends JpaRepository<CarreraTecnica, String> {

}
