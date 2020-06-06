package nl.dierenasiel.opdracht.services;

import lombok.NoArgsConstructor;
import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.dto.VerblijvenDto;
import nl.dierenasiel.opdracht.entities.Verblijf;
import nl.dierenasiel.opdracht.exception.EntityNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public class VerblijfService {

    private DierMapper dierMapper;

    private VerblijfDao verblijfDao;

    @Inject
    public VerblijfService(DierMapper dierMapper, VerblijfDao verblijfDao) {
        this.dierMapper = dierMapper;
        this.verblijfDao = verblijfDao;
    }

    public VerblijvenDto getVerblijven() {
        List<Verblijf> result = verblijfDao.findAll();

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
        Verblijf verblijf = new Verblijf();
        verblijf.setNaam(verblijfDto.getNaam());
        verblijf.setCapaciteit(verblijfDto.getCapaciteit());
        verblijf.setVerblijfType(verblijfDto.getVerblijfType());
        verblijfDao.save(verblijf);
    }


    public VerblijfDto getVerblijf(long verblijfId) {
        Verblijf verblijf = verblijfDao.findById(verblijfId).orElseThrow(EntityNotFoundException::new);
        VerblijfDto verblijfDto = new VerblijfDto();
        verblijfDto.setId(verblijf.getId());
        verblijfDto.setNaam(verblijf.getNaam());
        return verblijfDto;
    }

    public void updateVerblijf(long verblijfId, VerblijfDto verblijfDto) {
        Verblijf verblijf = verblijfDao.findById(verblijfId).orElseThrow(EntityNotFoundException::new);
        verblijf.setNaam(verblijfDto.getNaam());
        verblijf.setCapaciteit(verblijfDto.getCapaciteit());
        verblijfDao.save(verblijf);
    }

    public void deleteVerblijf(long verblijfId) {
        Verblijf verblijf = verblijfDao.findById(verblijfId).orElseThrow(EntityNotFoundException::new);
        verblijf.getDieren().forEach(dier -> dier.setVerblijf(null));
        verblijfDao.delete(verblijf);


    }
}
