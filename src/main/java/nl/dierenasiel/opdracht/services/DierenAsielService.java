package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.DierenDto;
import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.dto.VerblijvenDto;
import nl.dierenasiel.opdracht.model.DierEntity;
import nl.dierenasiel.opdracht.model.VerblijfEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DierenAsielService {

    @PersistenceContext
    private EntityManager em;

    public DierenDto getDieren() {
        List<DierEntity> dierEntityList = em.createNamedQuery("Dier.findAll").getResultList();
        DierenDto dierenDto = new DierenDto();

        List<DierDto> dierDtoList = new ArrayList<>();
        dierEntityList.forEach(dierEntity -> {
            dierDtoList.add(toDierDto(dierEntity));
        });

        dierenDto.setContent(dierDtoList);
        return dierenDto;
    }


    public DierDto getDier(long dierId) {
        DierEntity dierEntity = (DierEntity) em.createNamedQuery("Dier.findById").setParameter("id", dierId).getSingleResult();
        return toDierDto(dierEntity);

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
                dierDtoList.add(toDierDto(dierEntity));
            });
            verblijfDto.setDieren(dierDtoList);

            verblijfDtoList.add(verblijfDto);
        });

        VerblijvenDto verblijvenDto = new VerblijvenDto();
        verblijvenDto.setContent(verblijfDtoList);
        return verblijvenDto;
    }


    public void createVerblijf(VerblijfDto verblijf) {
        VerblijfEntity verblijfEntity = new VerblijfEntity();
        verblijfEntity.setNaam(verblijf.getNaam());
        em.persist(verblijfEntity);
    }

    private DierDto toDierDto(DierEntity dierEntity) {
        DierDto dierDto = new DierDto();
        dierDto.setId(dierEntity.getId());
        dierDto.setNaam(dierEntity.getNaam());
        dierDto.setRegistratieDatum(dierEntity.getRegistratieDatum());
        dierDto.setSoort(dierEntity.getSoort());

        VerblijfDto verblijfDto = new VerblijfDto();
        if (dierEntity.getVerblijf() != null) {
            verblijfDto.setId(dierEntity.getVerblijf().getId());
            verblijfDto.setNaam(dierEntity.getVerblijf().getNaam());
        }
        dierDto.setVerblijf(verblijfDto);
        return dierDto;
    }

}
