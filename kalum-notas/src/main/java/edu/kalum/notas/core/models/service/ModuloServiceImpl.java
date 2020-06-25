package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.IModuloDao;
import edu.kalum.notas.core.models.entity.CarreraTecnica;
import edu.kalum.notas.core.models.entity.Modulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloServiceImpl implements IModuloService {
    @Autowired
    private IModuloDao moduloDao;

    @Override
    public List<Modulo> findAll() {
        return moduloDao.findAll();
    }

    @Override
    public Modulo findById(String id) {
        return moduloDao.findById(id).orElse(null);
    }

    @Override
    public Modulo save(Modulo modulo) {
        return moduloDao.save(modulo);
    }

    @Override
    public void delete(Modulo modulo) {
        moduloDao.delete(modulo);
    }

    @Override
    public void delete(String id) {
        moduloDao.deleteById(id);
    }

    @Override
    public List<Modulo> findByCarreraTecnica(CarreraTecnica carreraTecnica) {
        return moduloDao.findByCarreraTecnica(carreraTecnica);
    }

    @Override
    public List<Modulo> buscarModulos(String carreraId) {
        return moduloDao.buscarModulos(carreraId);
    }


}
