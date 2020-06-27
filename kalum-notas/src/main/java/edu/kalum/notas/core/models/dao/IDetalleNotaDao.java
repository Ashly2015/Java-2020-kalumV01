package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.DetalleNota;
import edu.kalum.notas.core.models.entity.Seminario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDetalleNotaDao extends JpaRepository<DetalleNota,String> {
    public List<DetalleNota> findByDetalleActividad(DetalleActividad detalleActividad);

    @Query("select d from  DetalleNota d where d.detalleActividad.detalleActividadId=?1")
    public List<DetalleNota> buscarDetalleNotas(String detalleActividadId);
}
