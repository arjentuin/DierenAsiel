package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.DierenDto;
import nl.dierenasiel.opdracht.model.DierEntity;
import nl.dierenasiel.opdracht.model.VerblijfEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DierService {

    private DierMapper dierMapper;

    @PersistenceContext
    private EntityManager em;

    public DierService() {
    }

    @Inject
    public DierService(DierMapper dierMapper) {
        this.dierMapper = dierMapper;
    }

    public DierenDto getDieren() {
        List<DierEntity> dierEntityList = em.createNamedQuery("Dier.findAll").getResultList();
        DierenDto dierenDto = new DierenDto();

        List<DierDto> dierDtoList = new ArrayList<>();
        dierEntityList.forEach(dierEntity -> {
            dierDtoList.add(dierMapper.toDierDto(dierEntity));
        });

        dierenDto.setContent(dierDtoList);
        return dierenDto;
    }


    public DierDto getDier(long dierId) {
        DierEntity dierEntity = (DierEntity) em.createNamedQuery("Dier.findById").setParameter("id", dierId).getSingleResult();
        return dierMapper.toDierDto(dierEntity);

    }


    public void createDier(DierDto dierDto) {
        DierEntity dierEntity = new DierEntity();
        if (dierDto.getVerblijf() != null) {
            VerblijfEntity verblijfEntity = (VerblijfEntity) em.createNamedQuery("Verblijf.findById").setParameter("id", dierDto.getVerblijf().getId()).getSingleResult();
            dierEntity.setVerblijf(verblijfEntity);
        }
        dierEntity.setNaam(dierDto.getNaam());
        dierEntity.setSoort(dierDto.getSoort());
        dierEntity.setRegistratieDatum(LocalDateTime.now());

        em.persist(dierEntity);
    }

    public void updateDier(Long dierId, DierDto dier) {
        DierEntity dierEntity = (DierEntity) em.createNamedQuery("Dier.findById").setParameter("id", dierId).getSingleResult();
        dierEntity.setNaam(dier.getNaam());
        dierEntity.setSoort(dier.getSoort());
        if (dier.getVerblijf() != null) {
            VerblijfEntity verblijfEntity = (VerblijfEntity) em.createNamedQuery("Verblijf.findById").setParameter("id", dier.getVerblijf().getId()).getSingleResult();
            dierEntity.setVerblijf(verblijfEntity);
        }
        em.persist(dierEntity);
    }

    public void deleteDier(long dierId) {
        em.createNamedQuery("Dier.deleteById").setParameter("id", dierId).executeUpdate();
    }
}
