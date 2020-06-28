package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.IAlumnoDao;
import edu.kalum.notas.core.models.entity.Alumno;
import edu.kalum.notas.core.models.entity.DetalleNota;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumnoServiceImpl {
    private Logger logger= LoggerFactory.getLogger(Alumno.class);
    @Autowired
    private IAlumnoDao alumnoDao;
    @Override
    public List<Alumno> findAll() {
        logger.info("Iniciando metodo de consultas de alumno");
        return alumnoDao.findAll();
    }

    @Override
    public Alumno findById(String id) {
        logger.info("Iniciando metodo de consultas de alumno por id");
        return alumnoDao.findById(id).orElse(null);
    }

    @Override
    public Alumno save(Alumno alumno) {
        logger.info("Iniciando metodo de guardar alumno");
        return alumnoDao.save(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        logger.info("Iniciando metodo de eliminacion de alumno");
        alumnoDao.delete(alumno);
    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de alumno por id");
        alumnoDao.deleteById(id);

    }



}
