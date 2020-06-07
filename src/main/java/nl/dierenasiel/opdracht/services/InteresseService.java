package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.PersoonDao;
import nl.dierenasiel.opdracht.dto.PersoonDto;
import nl.dierenasiel.opdracht.entities.Interesse;
import nl.dierenasiel.opdracht.entities.Persoon;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class InteresseService {

    private PersoonDao persoonDao;

    public InteresseService() {
    }

    @Inject
    public InteresseService(PersoonDao persoonDao) {
        this.persoonDao = persoonDao;
    }

    public void createGeinteresseerde(PersoonDto persoonDto) {
        Persoon persoon = new Persoon();
        persoon.setNaam(persoonDto.getNaam());
        persoon.setEmailadres(persoonDto.getEmailadres());
        persoon.setRegistratieDatum(LocalDateTime.now());

        Set<Interesse> interesseList = new HashSet<>();
        persoonDto.getSoort().forEach(dierSoort -> {
            Interesse interesse = new Interesse();
            interesse.setSoort(dierSoort);
            interesse.setPersoon(persoon);
            interesseList.add(interesse);
        });
        persoon.setInteresse(interesseList);
        persoonDao.save(persoon);
    }

}
