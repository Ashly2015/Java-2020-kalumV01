package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.IAlumnoDao;
import edu.kalum.notas.core.models.dao.IAsignacionAlumnoDao;
import edu.kalum.notas.core.models.entity.Alumno;
import edu.kalum.notas.core.models.entity.AsignacionAlumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionAlumnoServiceImpl implements IAsignacionAlumnoService{
    private Logger logger= LoggerFactory.getLogger(AsignacionAlumno.class);
    @Autowired
    private IAsignacionAlumnoDao asignacionAlumnoDao;
    @Override
    public List<AsignacionAlumno> findAll() {
        logger.info("Iniciando metodo de consultas de asignacion alumno");
        return asignacionAlumnoDao.findAll();
    }

    @Override
    public AsignacionAlumno findById(String id) {
        logger.info("Iniciando metodo de consultas de asignacion alumno por id");
        return asignacionAlumnoDao.findById(id).orElse(null);
    }

    @Override
    public AsignacionAlumno save(AsignacionAlumno asignacionAlumno) {
        logger.info("Iniciando metodo de  asignacion alumno");
        return asignacionAlumnoDao.save(asignacionAlumno);
    }

    @Override
    public void delete(AsignacionAlumno asignacionAlumno) {
        logger.info("Iniciando metodo de eliminacion de asignacion alumno");
        asignacionAlumnoDao.delete(asignacionAlumno);
    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de asignacion alumno por id");
        asignacionAlumnoDao.deleteById(id);
    }
}
