package org.rachelbarrios.test.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Conexion {
    private final String PERSISTENCE_UNIT_NAME = "KalumPU";
    private EntityManager entityManager;
    private static Conexion instancia;

    public Conexion() {
        try {
            this.entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public List<?> findAll(String query) {
        return this.entityManager.createNamedQuery(query).getResultList();
    }

    public void agregar(Object objeto) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            this.entityManager.persist(objeto);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public void eliminar(Object objeto) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            this.entityManager.remove(objeto);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public void modificar(Object objeto) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            this.entityManager.persist(objeto);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public Object buscar(String id, Class<?> clase) {
        return this.entityManager.find(clase, id);
    }

    public Object buscar(int id, Class<?> clase) {
        return this.entityManager.find(clase, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
}
