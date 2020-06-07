package nl.dierenasiel.opdracht.mapper;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.entities.Dier;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DierMapper {

    public DierMapper() {
    }

    public DierDto toDierDto(Dier dier) {
        DierDto dierDto = new DierDto();
        dierDto.setId(dier.getId());
        dierDto.setNaam(dier.getNaam());
        dierDto.setRegistratieDatum(dier.getRegistratieDatum());
        dierDto.setSoort(dier.getSoort());

        VerblijfDto verblijfDto = new VerblijfDto();
        if (dier.getVerblijf() != null) {
            verblijfDto.setId(dier.getVerblijf().getId());
            verblijfDto.setNaam(dier.getVerblijf().getNaam());
            verblijfDto.setCapaciteit(dier.getVerblijf().getCapaciteit());
        }
        dierDto.setVerblijf(verblijfDto);
        return dierDto;
    }

}
