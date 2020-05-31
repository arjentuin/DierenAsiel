package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.model.DierEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DierDao {

    @PersistenceContext
    private EntityManager em;

    public DierDao() {
    }

    public List<DierEntity> findAll() {
        return em.createNamedQuery("Dier.findAll").getResultList();
    }

    public List<DierEntity> getDierById(long dierId) {
        return em.createNamedQuery("Dier.findById").setParameter("id", dierId).getResultList();
    }

    public void saveDier(DierEntity dierEntity) {
        em.persist(dierEntity);
    }

    public void deleteDier(long dierId) {
        em.createNamedQuery("Dier.deleteById").setParameter("id", dierId).executeUpdate();
    }
}
