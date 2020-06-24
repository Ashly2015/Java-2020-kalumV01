package edu.kalum.notas.core.models.service;

import edu.kalum.notas.core.models.entity.Modulo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IModuloService {
    public List<Modulo> findAll();
    public Modulo findById(String id);
    public Modulo save(Modulo modulo);
    public void delete(Modulo modulo);
    public void delete(String id);
}
