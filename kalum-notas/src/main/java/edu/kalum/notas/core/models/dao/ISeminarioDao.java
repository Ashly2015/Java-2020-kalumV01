package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entity.Seminario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISeminarioDao extends JpaRepository<Seminario,String> {
}
