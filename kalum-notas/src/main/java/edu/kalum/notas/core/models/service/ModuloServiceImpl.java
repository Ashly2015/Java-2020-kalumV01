package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.IModuloDao;
import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloServiceImpl implements IModuloService {
    private Logger logger= LoggerFactory.getLogger(ModuloServiceImpl.class);
    @Autowired
    private IModuloDao moduloDao;

    @Override
    public List<Modulo> findAll() {
        logger.info("Iniciando metodo de consultas de modulos");
        return moduloDao.findAll();
    }

    @Override
    public Modulo findById(String id) {

        logger.info("Iniciando metodo de consultas de modulos por id");
        return moduloDao.findById(id).orElse(null);
    }

    @Override
    public Modulo save(Modulo modulo) {
        logger.info("Iniciando metodo de guardar modulos");
        return moduloDao.save(modulo);
    }

    @Override
    public void delete(Modulo modulo) {
        logger.info("Iniciando metodo de eliminacion de modulos");
        moduloDao.delete(modulo);
    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de modulos por id");
        moduloDao.deleteById(id);
    }

    @Override
    public List<Modulo> findByCarreraTecnica(CarreraTecnica carreraTecnica) {
        logger.info("Iniciando metodo de consultas de carrera tecnica correspondiente al modulo");
        return moduloDao.findByCarreraTecnica(carreraTecnica);
    }

    @Override
    public List<Modulo> buscarModulos(String carreraId) {
        logger.info("Iniciando metodo de consultas de carrera tecnica correspondiente al modulo por id");
        return moduloDao.buscarModulos(carreraId);
    }


}
