package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.model.VerblijfEntity;
import nl.dierenasiel.opdracht.enums.VerblijfType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VerblijfDao {

    @PersistenceContext
    private EntityManager em;

    public VerblijfDao() {
    }

    public List<VerblijfEntity> findAll() {
        return em.createNamedQuery("Verblijf.findAll").getResultList();
    }

    public List<VerblijfEntity> getVerblijfByType(VerblijfType verblijfType) {
        return em.createNamedQuery("Verblijf.findByVerblijfType", VerblijfEntity.class).setParameter("verblijfType", verblijfType).getResultList();
    }

    public List<VerblijfEntity> findById(long verblijfId) {
        return em.createNamedQuery("Verblijf.findById", VerblijfEntity.class).setParameter("id", verblijfId).getResultList();
    }


    public void saveVerblijf(VerblijfEntity verblijfEntity) {
        em.persist(verblijfEntity);
    }

    public void deleteVerblijf(long verblijfId) {
        em.createNamedQuery("Verblijf.deleteById").setParameter("id", verblijfId).executeUpdate();
    }
}
