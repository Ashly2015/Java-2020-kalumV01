package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.IDetalleActividadDao;
import edu.kalum.notas.core.models.dao.IDetalleNotaDao;
import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.DetalleNota;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleNotaServiceImpl implements IDetalleNotaService{
    private Logger logger= LoggerFactory.getLogger(DetalleNota.class);
    @Autowired
    private IDetalleNotaDao detalleNotaDao;
    @Override
    public List<DetalleNota> findAll() {
        logger.info("Iniciando metodo de consultas de detalle nota");
        return detalleNotaDao.findAll();
    }

    @Override
    public DetalleNota findById(String id) {
        logger.info("Iniciando metodo de consultas de detalle nota por id");
        return detalleNotaDao.findById(id).orElse(null);
    }

    @Override
    public DetalleNota save(DetalleNota detalleNota) {
        logger.info("Iniciando metodo de guardar detalle nota");
        return detalleNotaDao.save(detalleNota);
    }

    @Override
    public void delete(DetalleNota detalleNota) {
        logger.info("Iniciando metodo de eliminacion de detalle nota");
        detalleNotaDao.delete(detalleNota);
    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de detalle nota por id");
        detalleNotaDao.deleteById(id);

    }

    @Override
    public List<DetalleNota> findByDetalleActividad(DetalleActividad detalleActividad) {
        logger.info("Iniciando metodo de consultas de detalle actividad correspondiente al detalle nota");
        return detalleNotaDao.findBySeminario(detalleActividad);
    }

    @Override
    public List<DetalleNota> buscarDetalleNotas(String detalleActividadId) {
        logger.info("Iniciando metodo de consultas de detalle actividad correspondiente al detalle nota por id");
        return detalleNotaDao.buscarDetalleNotas(detalleActividadId);
    }
}
