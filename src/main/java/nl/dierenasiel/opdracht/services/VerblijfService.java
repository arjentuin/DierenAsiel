package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.dto.VerblijvenDto;
import nl.dierenasiel.opdracht.exception.EntityNotFoundException;
import nl.dierenasiel.opdracht.model.VerblijfEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VerblijfService {

    private DierMapper dierMapper;

    private VerblijfDao verblijfDao;

    public VerblijfService() {
    }

    @Inject
    public VerblijfService(DierMapper dierMapper, VerblijfDao verblijfDao) {
        this.dierMapper = dierMapper;
        this.verblijfDao = verblijfDao;
    }

    public VerblijvenDto getVerblijven() {
        List<VerblijfEntity> result = verblijfDao.findAll();

        List<VerblijfDto> verblijfDtoList = new ArrayList<>();
        result.forEach(verblijfEntity -> {
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
        verblijfEntity.setCapaciteit(verblijfDto.getCapaciteit());
        verblijfEntity.setVerblijfType(verblijfDto.getVerblijfType());
        verblijfDao.saveVerblijf(verblijfEntity);
    }


    public VerblijfDto getVerblijf(long verblijfId) {
        VerblijfEntity verblijfEntity = verblijfDao.findById(verblijfId).stream().findFirst().orElseThrow(EntityNotFoundException::new);
        VerblijfDto verblijfDto = new VerblijfDto();
        verblijfDto.setId(verblijfEntity.getId());
        verblijfDto.setNaam(verblijfEntity.getNaam());
        return verblijfDto;
    }

    public void updateVerblijf(long verblijfId, VerblijfDto verblijfDto) {
        VerblijfEntity verblijfEntity = verblijfDao.findById(verblijfId).stream().findFirst().orElseThrow(EntityNotFoundException::new);
        verblijfEntity.setNaam(verblijfDto.getNaam());
        verblijfEntity.setCapaciteit(verblijfDto.getCapaciteit());
        verblijfDao.saveVerblijf(verblijfEntity);
    }

    public void deleteVerblijf(long verblijfId) {
        VerblijfEntity verblijfEntity = verblijfDao.findById(verblijfId).stream().findFirst().orElseThrow(EntityNotFoundException::new);
        verblijfEntity.getDieren().forEach(dier -> dier.setVerblijf(null));
        verblijfDao.deleteVerblijf(verblijfId);


    }
}
