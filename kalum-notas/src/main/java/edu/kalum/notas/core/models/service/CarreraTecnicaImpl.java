package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.dao.CarreraTecnicaDao;
import edu.kalum.notas.core.models.entity.CarreraTecnica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraTecnicaImpl implements ICarreraTecnicaService{
    @Autowired
    private CarreraTecnicaDao carreraTecnicaDao;
    @Override
    public List<CarreraTecnica> findAll() {
        return carreraTecnicaDao.findAll();
    }

    @Override
    public CarreraTecnica findById(String id) {
        return carreraTecnicaDao.findById(id).orElse(null);
    }

    @Override
    public CarreraTecnica save(CarreraTecnica carreraTecnica) {
        return carreraTecnicaDao.save(carreraTecnica);
    }

    @Override
    public void delete(CarreraTecnica carreraTecnica) {
        carreraTecnicaDao.delete(carreraTecnica);

    }

    @Override
    public void delete(String id) {
        carreraTecnicaDao.deleteById(id);

    }
}
