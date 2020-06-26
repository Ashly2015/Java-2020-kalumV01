package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.entity.Seminario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISeminarioDao extends JpaRepository<Seminario,String> {

    public List<Seminario> findByModulo(Modulo modulo);

    @Query("select s from  Seminario s where s.modulo.moduloId=?1")
    public List<Seminario> buscarSeminarios(String moduloId);

}
