package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.controllers.CarreraTecnicaController;
import edu.kalum.notas.core.models.dao.CarreraTecnicaDao;
import edu.kalum.notas.core.models.entity.CarreraTecnica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraTecnicaImpl implements ICarreraTecnicaService{
    private Logger logger= LoggerFactory.getLogger(CarreraTecnicaImpl.class);
    @Autowired
    private CarreraTecnicaDao carreraTecnicaDao;
    @Override
    public List<CarreraTecnica> findAll() {
        logger.info("Iniciando metodo de consultas de carreras tecnincas");
        return carreraTecnicaDao.findAll();
    }

    @Override
    public CarreraTecnica findById(String id) {
        logger.info("Iniciando metodo de consultas de carreras tecnincas por id");
        return carreraTecnicaDao.findById(id).orElse(null);
    }

    @Override
    public CarreraTecnica save(CarreraTecnica carreraTecnica) {
        logger.info("Iniciando metodo de guardar  carreras tecnincas");
        return carreraTecnicaDao.save(carreraTecnica);
    }

    @Override
    public void delete(CarreraTecnica carreraTecnica) {
        logger.info("Iniciando metodo de eliminacion de carreras tecnincas");
        carreraTecnicaDao.delete(carreraTecnica);

    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de carreras tecnincas por id");
        carreraTecnicaDao.deleteById(id);

    }
}
