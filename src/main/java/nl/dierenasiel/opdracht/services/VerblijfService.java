package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.dto.VerblijvenDto;
import nl.dierenasiel.opdracht.model.VerblijfEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VerblijfService {

    private DierMapper dierMapper;

    @PersistenceContext
    private EntityManager em;

    public VerblijfService() {
    }

    @Inject
    public VerblijfService(DierMapper dierMapper) {
        this.dierMapper = dierMapper;
    }

    public VerblijvenDto getVerblijven() {
        List<VerblijfEntity> verblijfEntityList = em.createNamedQuery("Verblijf.findAll").getResultList();

        List<VerblijfDto> verblijfDtoList = new ArrayList<>();
        verblijfEntityList.forEach(verblijfEntity -> {
            VerblijfDto verblijfDto = new VerblijfDto();
            verblijfDto.setId(verblijfEntity.getId());
            verblijfDto.setNaam(verblijfEntity.getNaam());

            List<DierDto> dierDtoList = new ArrayList<>();
            verblijfEntity.getDieren().forEach(dierEntity -> {
                dierDtoList.add(dierMapper.toDierDto(dierEntity));
            });
            verblijfDto.setDieren(dierDtoList);

            verblijfDtoList.add(verblijfDto);
        });

        VerblijvenDto verblijvenDto = new VerblijvenDto();
        verblijvenDto.setContent(verblijfDtoList);
        return verblijvenDto;
    }


    public void createVerblijf(VerblijfDto verblijfDto) {
        VerblijfEntity verblijfEntity = new VerblijfEntity();
        verblijfEntity.setNaam(verblijfDto.getNaam());
        em.persist(verblijfEntity);
    }


    public VerblijfDto getVerblijf(long verblijfId) {
        VerblijfEntity verblijfEntity = (VerblijfEntity) em.createNamedQuery("Verblijf.findById").setParameter("id", verblijfId).getSingleResult();
        VerblijfDto verblijfDto = new VerblijfDto();
        verblijfDto.setId(verblijfEntity.getId());
        verblijfDto.setNaam(verblijfEntity.getNaam());
        return verblijfDto;
    }

    public void updateVerblijf(long verblijfId, VerblijfDto verblijfDto) {
        VerblijfEntity verblijfEntity = (VerblijfEntity) em.createNamedQuery("Verblijf.findById").setParameter("id", verblijfId).getSingleResult();
        verblijfEntity.setNaam(verblijfDto.getNaam());
        em.persist(verblijfEntity);
    }

    public void deleteVerblijf(long verblijfId) {
        VerblijfEntity verblijfEntity = (VerblijfEntity) em.createNamedQuery("Verblijf.findById").setParameter("id", verblijfId).getSingleResult();
        verblijfEntity.getDieren().forEach(dier -> dier.setVerblijf(null));
        em.createNamedQuery("Verblijf.deleteById").setParameter("id", verblijfId).executeUpdate();

    }
}
