package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.DierDao;
import nl.dierenasiel.opdracht.dao.DierDsl;
import nl.dierenasiel.opdracht.dao.InteresseDao;
import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.DierenDto;
import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.entities.Interesse;
import nl.dierenasiel.opdracht.entities.Verblijf;
import nl.dierenasiel.opdracht.enums.DierSoort;
import nl.dierenasiel.opdracht.exception.EntityNotFoundException;
import nl.dierenasiel.opdracht.exception.PreConditionFailedException;
import nl.dierenasiel.opdracht.mapper.DierMapper;
import nl.dierenasiel.opdracht.mapper.PersoonMapper;
import nl.dierenasiel.opdracht.messaging.Sender;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class DierService {

    private DierMapper dierMapper;

    private DierDao dierDao;

    private DierDsl dierDsl;

    private VerblijfDao verblijfDao;

    private InteresseDao interesseDao;

    private Sender sender;

    private PersoonMapper persoonMapper;

    public DierService() {
    }

    @Inject
    public DierService(DierMapper dierMapper, DierDao dierDao, DierDsl dierDsl, VerblijfDao verblijfDao, InteresseDao interesseDao, Sender sender, PersoonMapper persoonMapper) {
        this.dierMapper = dierMapper;
        this.dierDao = dierDao;
        this.dierDsl = dierDsl;
        this.verblijfDao = verblijfDao;
        this.interesseDao = interesseDao;
        this.sender = sender;
        this.persoonMapper = persoonMapper;
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
        Dier dier = dierDsl.getDierById(dierId);
        return dierMapper.toDierDto(dier);
    }

    public Dier registerDier(DierDto dierDto) {
        Dier dier = new Dier();

        dier.setNaam(dierDto.getNaam());
        dier.setSoort(dierDto.getSoort());
        dier.setRegistratieDatum(LocalDateTime.now());

        List<Verblijf> verblijfList = verblijfDao.findVerblijfEntitiesByVerblijfType(dierDto.getVerblijfType())
                .orElseThrow(() -> new PreConditionFailedException(String.format("No '%s' verblijf for animal with name '%s'", dierDto.getVerblijfType(), dierDto.getNaam())));

        verblijfList.stream()
                .filter(v -> v.getDieren().size() < v.getCapaciteit())
                .findFirst()
                .ifPresent(dier::setVerblijf);
        if (dier.getVerblijf() == null) {
            throw new PreConditionFailedException("Out of space, no capacity available.");
        }
        return dierDao.save(dier);
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

    public List<DierDto> getBeschikbareDierenInSoorten(List<DierSoort> dierSoortSet) {
        List<DierDto> dierDtoList = new ArrayList<>();
        dierDao.findDiersByIsBeschikbaarAndSoortIn(1, dierSoortSet).orElse(Collections.emptyList()).forEach(dier -> {
            dierDtoList.add(dierMapper.toDierDto(dier));
        });
        return dierDtoList;
    }

    public void adoptDier(long dierId) {
        Dier dier = dierDao.findById(dierId).orElseThrow(EntityNotFoundException::new);
        dier.setIsBeschikbaar(0);
        dierDao.save(dier);
    }

    public void sendGeinteresseerden(Dier dier) {
        interesseDao.findBySoort(dier.getSoort()).orElse(Collections.emptyList()).stream()
                .map(Interesse::getPersoon)
                .map(persoonMapper::toPersoonDto)
                .collect(Collectors.toList()).forEach(persoonDto -> sender.sendMessage(persoonDto.getEmailadres()));
    }
}
