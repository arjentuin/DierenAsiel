package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.DierDao;
import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.DierenDto;
import nl.dierenasiel.opdracht.exception.EntityNotFoundException;
import nl.dierenasiel.opdracht.exception.PreConditionFailedException;
import nl.dierenasiel.opdracht.model.DierEntity;
import nl.dierenasiel.opdracht.model.VerblijfEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DierService {

    private DierMapper dierMapper;

    private DierDao dierDao;

    private VerblijfDao verblijfDao;

    public DierService() {
    }

    @Inject
    public DierService(DierMapper dierMapper, DierDao dierDao, VerblijfDao verblijfDao) {
        this.dierMapper = dierMapper;
        this.dierDao = dierDao;
        this.verblijfDao = verblijfDao;
    }

    public DierenDto getDieren() {
        List<DierEntity> dierEntityList = dierDao.findAll();
        DierenDto dierenDto = new DierenDto();
        List<DierDto> dierDtoList = new ArrayList<>();
        dierEntityList.forEach(dierEntity -> dierDtoList.add(dierMapper.toDierDto(dierEntity)));
        dierenDto.setContent(dierDtoList);
        return dierenDto;
    }

    public DierDto getDier(long dierId) {
        List<DierEntity> result = dierDao.getDierById(dierId);
        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        DierEntity dierEntity = result.get(0);
        return dierMapper.toDierDto(dierEntity);
    }


    public void createDier(DierDto dierDto) {
        DierEntity dierEntity = new DierEntity();

        dierEntity.setNaam(dierDto.getNaam());
        dierEntity.setSoort(dierDto.getSoort());
        dierEntity.setRegistratieDatum(LocalDateTime.now());

        List<VerblijfEntity> result = verblijfDao.getVerblijfByType(dierDto.getVerblijfType());

        if (result.isEmpty()) {
            throw new PreConditionFailedException(String.format("No [%s] verblijf for animal with name [%s]", dierDto.getVerblijfType(), dierDto.getNaam()));
        } else {
            result.stream()
                    .filter(v -> v.getDieren().size() < v.getCapaciteit())
                    .findFirst()
                    .ifPresent(dierEntity::setVerblijf);
            if (dierEntity.getVerblijf() == null) {
                throw new PreConditionFailedException("Out of space, no capacity available.");
            }
        }
        dierDao.saveDier(dierEntity);
    }

    public void updateDier(Long dierId, DierDto dierDto) {
        List<DierEntity> result = dierDao.getDierById(dierId);
        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        DierEntity dierEntity = result.get(0);
        dierEntity.setNaam(dierDto.getNaam());
        dierEntity.setSoort(dierDto.getSoort());
        if (dierDto.getVerblijf() != null) {
            VerblijfEntity verblijfEntity = verblijfDao.findById(dierDto.getVerblijf().getId()).stream().findFirst().orElseThrow(EntityNotFoundException::new);
            dierEntity.setVerblijf(verblijfEntity);
        }
        dierDao.saveDier(dierEntity);
    }

    public void deleteDier(long dierId) {
        dierDao.deleteDier(dierId);
    }
}
