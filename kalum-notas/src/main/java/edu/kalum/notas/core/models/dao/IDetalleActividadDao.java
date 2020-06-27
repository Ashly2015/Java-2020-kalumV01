package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.entity.Seminario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDetalleActividadDao extends JpaRepository<DetalleActividad,String> {
    public List<DetalleActividad> findBySeminario(Seminario seminario);

    @Query("select d from  DetalleActividad d where d.seminario.seminarioId=?1")
    public List<DetalleActividad> buscarDetalleActividades(String seminarioId);
}
