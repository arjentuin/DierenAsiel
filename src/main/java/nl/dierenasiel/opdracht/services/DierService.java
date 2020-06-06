package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.DierDao;
import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.DierenDto;
import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.entities.Verblijf;
import nl.dierenasiel.opdracht.exception.EntityNotFoundException;
import nl.dierenasiel.opdracht.exception.PreConditionFailedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
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
        List<Dier> dierList = dierDao.findAll();
        DierenDto dierenDto = new DierenDto();
        List<DierDto> dierDtoList = new ArrayList<>();
        dierList.forEach(dierEntity -> dierDtoList.add(dierMapper.toDierDto(dierEntity)));
        dierenDto.setContent(dierDtoList);
        return dierenDto;
    }

    public DierDto getDier(long dierId) {
        Dier dier = dierDao.findById(dierId).orElseThrow(EntityNotFoundException::new);
        return dierMapper.toDierDto(dier);
    }

    public void createDier(DierDto dierDto) {
        Dier dier = new Dier();

        dier.setNaam(dierDto.getNaam());
        dier.setSoort(dierDto.getSoort());
        dier.setRegistratieDatum(LocalDateTime.now());

        List<Verblijf> verblijfList = verblijfDao.findVerblijfEntitiesByVerblijfType(dierDto.getVerblijfType())
                .orElseThrow(() -> new PreConditionFailedException(String.format("No [%s] verblijf for animal with name [%s]", dierDto.getVerblijfType(), dierDto.getNaam())));

        verblijfList.stream()
                .filter(v -> v.getDieren().size() < v.getCapaciteit())
                .findFirst()
                .ifPresent(dier::setVerblijf);
        if (dier.getVerblijf() == null) {
            throw new PreConditionFailedException("Out of space, no capacity available.");
        }
        dierDao.save(dier);
    }

    public void updateDier(Long dierId, DierDto dierDto) {
        Dier dier = dierDao.findById(dierId).orElseThrow(EntityNotFoundException::new);
        dier.setNaam(dierDto.getNaam());
        dier.setSoort(dierDto.getSoort());
        if (dierDto.getVerblijf() != null) {
            Verblijf verblijf = verblijfDao.findById(dierDto.getVerblijf().getId()).orElseThrow(EntityNotFoundException::new);
            dier.setVerblijf(verblijf);
        }

    }

    public void deleteDier(long dierId) {
        dierDao.deleteById(dierId);
    }
}
