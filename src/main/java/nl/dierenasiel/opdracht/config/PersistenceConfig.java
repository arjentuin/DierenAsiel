package nl.dierenasiel.opdracht.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Produces
    @Dependent
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
