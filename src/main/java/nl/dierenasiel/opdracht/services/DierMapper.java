package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.model.DierEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DierMapper {

    public DierMapper() {
    }

    public DierDto toDierDto(DierEntity dierEntity) {
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
