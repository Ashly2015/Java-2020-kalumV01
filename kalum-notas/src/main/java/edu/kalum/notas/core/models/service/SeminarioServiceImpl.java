package edu.kalum.notas.core.models.service;
import edu.kalum.notas.core.models.dao.IModuloDao;
import edu.kalum.notas.core.models.dao.ISeminarioDao;
import edu.kalum.notas.core.models.entity.Modulo;
import edu.kalum.notas.core.models.entity.Seminario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeminarioServiceImpl implements ISeminarioService{
    private Logger logger= LoggerFactory.getLogger(SeminarioServiceImpl.class);
    @Autowired
    private ISeminarioDao seminarioDao;

    @Override
    public List<Seminario> findAll() {
        logger.info("Iniciando metodo de consultas de seminarios");
        return seminarioDao.findAll();
    }

    @Override
    public Seminario findById(String id) {
        logger.info("Iniciando metodo de consultas de seminario por id");
        return seminarioDao.findById(id).orElse(null);
    }

    @Override
    public Seminario save(Seminario seminario) {
        logger.info("Iniciando metodo de guardar seminario");
        return seminarioDao.save(seminario);
    }

    @Override
    public void delete(Seminario seminario) {
        logger.info("Iniciando metodo de eliminacion de seminarios");
        seminarioDao.delete(seminario);
    }

    @Override
    public void delete(String id) {
        logger.info("Iniciando metodo de eliminacion de seminario por id");
        seminarioDao.deleteById(id);
    }

    @Override
    public List<Seminario> findByModulo(Modulo modulo) {
        logger.info("Iniciando metodo de consultas de smodulo correspondiente al seminario");
        return seminarioDao.findByModulo(modulo);
    }

    @Override
    public List<Seminario> buscarSeminarios(String moduloId) {
        logger.info("Iniciando metodo de consultas de modulo correspondiente al seminario por id");
        return seminarioDao.buscarSeminarios(moduloId);
    }
}
