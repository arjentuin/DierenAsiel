package nl.dierenasiel.opdracht.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceConfig {
    @Produces
    @Dependent
    @PersistenceContext
    private EntityManager entityManager;


}
