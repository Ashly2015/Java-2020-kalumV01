package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.DetalleNota;
import edu.kalum.notas.core.models.entity.Seminario;

import java.util.List;

public interface IDetalleNotaService {
    public List<DetalleNota> findAll();
    public DetalleNota findById(String id);
    public DetalleNota save(DetalleNota detalleNota);
    public void delete(DetalleNota detalleNota);
    public void delete(String id);
    public List<DetalleNota> findByDetalleActividad(DetalleActividad detalleActividad);
    public List<DetalleNota> buscarDetalleNotas(String detalleActividadId);
}
