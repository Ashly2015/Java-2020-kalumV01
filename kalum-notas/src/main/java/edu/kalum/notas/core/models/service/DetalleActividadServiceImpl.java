package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.IDetalleActividadDao;
import edu.kalum.notas.core.models.entity.DetalleActividad;
import edu.kalum.notas.core.models.entity.Seminario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleActividadServiceImpl implements IDetalleActividadService {
    private Logger logger= LoggerFactory.getLogger(DetalleActividadServiceImpl.class);
    @Autowired
    private IDetalleActividadDao detalleActividadDao;

    @Override
    public List<DetalleActividad> findAll() {
        logger.info("Iniciando metodo de consultas de detalle actividad");
        return detalleActividadDao.findAll();
    }

    @Override
    public DetalleActividad findById(String id) {
        logger.info("Iniciando metodo de consultas de detalle actividad por id");
        return detalleActividadDao.findById(id).orElse(null);
    }

    @Override
    public DetalleActividad save(DetalleActividad detalleActividad) {
        logger.info("Iniciando metodo de guardar detalle actividad");
        return detalleActividadDao.save(detalleActividad);
    }

    @Override
    public void delete(DetalleActividad detalleActividad) {
        logger.info("Iniciando metodo de eliminacion de detalle actividad");
        detalleActividadDao.delete(detalleActividad);


    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de detalle actividad por id");
        detalleActividadDao.deleteById(id);
    }

    @Override
    public List<DetalleActividad> findBySeminario(Seminario seminario) {
        logger.info("Iniciando metodo de consultas de seminario correspondiente al detalle actividad");
        return detalleActividadDao.findBySeminario(seminario);
    }

    @Override
    public List<DetalleActividad> buscarDetalleActividades(String seminarioId) {
        logger.info("Iniciando metodo de consultas de seminario correspondiente al detalle actividad por id");
        return detalleActividadDao.buscarDetalleActividad(seminarioId);
    }
}
