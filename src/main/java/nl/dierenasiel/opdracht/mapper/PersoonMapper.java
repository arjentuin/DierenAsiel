package nl.dierenasiel.opdracht.mapper;

import nl.dierenasiel.opdracht.dto.PersoonDto;
import nl.dierenasiel.opdracht.entities.Persoon;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersoonMapper {

    public PersoonMapper() {
    }

    public PersoonDto toPersoonDto(Persoon persoon) {
        PersoonDto persoonDto = new PersoonDto();
        persoonDto.setNaam(persoon.getNaam());
        persoonDto.setEmailadres(persoon.getEmailadres());
        return persoonDto;
    }
}
