package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.enums.DierSoort;
import nl.dierenasiel.opdracht.entities.Verblijf;
import nl.dierenasiel.opdracht.enums.VerblijfType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class DierMapperTest {

    @InjectMocks
    private DierMapper cut;

    @Test
    void testMappingToDierDto() {
        final long id = 1L;
        final String naam = "Dikkie";
        final DierSoort soort = DierSoort.KONIJN;

        final long verblijfId = 2L;
        final int verblijfCapaciteit = 24;
        final String verblijfNaam = "Konijnen oord";
        final VerblijfType verblijfType = VerblijfType.KNAAGDIEREN;
        final LocalDateTime registratieDatum = LocalDateTime.of(2020, 6, 1, 12, 34, 45);
        Verblijf verblijf = new Verblijf();
        verblijf.setId(verblijfId);
        verblijf.setCapaciteit(verblijfCapaciteit);
        verblijf.setNaam(verblijfNaam);
        verblijf.setVerblijfType(verblijfType);

        Dier dier = new Dier();
        dier.setId(id);
        dier.setNaam(naam);
        dier.setSoort(soort);
        dier.setVerblijf(verblijf);

        dier.setRegistratieDatum(registratieDatum);
        DierDto result = cut.toDierDto(dier);

        assertThat(result.getId(), is(id));
        assertThat(result.getNaam(), is(naam));
        assertThat(result.getSoort(), is(soort));
        assertThat(result.getRegistratieDatum(), is(registratieDatum));

        assertThat(result.getVerblijf().getId(), is(verblijfId));
        assertThat(result.getVerblijf().getNaam(), is(verblijfNaam));
        assertThat(result.getVerblijf().getCapaciteit(), is(verblijfCapaciteit));

    }
}
