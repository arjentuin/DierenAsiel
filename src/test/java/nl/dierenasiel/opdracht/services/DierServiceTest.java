package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.DierDao;
import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.entities.Verblijf;
import nl.dierenasiel.opdracht.enums.DierSoort;
import nl.dierenasiel.opdracht.enums.VerblijfType;
import nl.dierenasiel.opdracht.exception.PreConditionFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DierServiceTest {

    private final static String NAAM = "Kwak";

    @Mock
    private DierMapper dierMapper;

    @Mock
    private DierDao dierDao;

    @Mock
    private VerblijfDao verblijfDao;

    @Captor
    private ArgumentCaptor<Dier> dierEntityArgumentCaptor;

    @InjectMocks
    private DierService cut;

    @Test
    void testCreateDier_NoVerblijf() {
        final DierDto dierDto = new DierDto();
        dierDto.setNaam(NAAM);
        dierDto.setSoort(DierSoort.EEND);
        dierDto.setVerblijfType(VerblijfType.GEVOGELTE);

        when(verblijfDao.findVerblijfEntitiesByVerblijfType(VerblijfType.GEVOGELTE)).thenReturn(Optional.of(Collections.EMPTY_LIST));

        assertThrows(PreConditionFailedException.class, () -> {
            cut.createDier(dierDto);
        });
    }

    @Test
    void testCreateDierVerblijf_Full() {
        final DierDto dierDto = new DierDto();
        dierDto.setNaam(NAAM);
        dierDto.setSoort(DierSoort.EEND);
        dierDto.setVerblijfType(VerblijfType.GEVOGELTE);


        Verblijf verblijf = createVerblijfEntity(VerblijfType.GEVOGELTE, 2);
        Set<Dier> dierSet = new HashSet<>();
        dierSet.add(createDierEntity("Kwik", verblijf));
        dierSet.add(createDierEntity("Kwek", verblijf));
        verblijf.setDieren(dierSet);

        List<Verblijf> verblijfList = new ArrayList<>();
        verblijfList.add(verblijf);

        when(verblijfDao.findVerblijfEntitiesByVerblijfType(VerblijfType.GEVOGELTE)).thenReturn(Optional.of(verblijfList));

        assertThrows(PreConditionFailedException.class, () -> {
            cut.createDier(dierDto);
        });
    }

    @Test
    void testCreateDierVerblijf_OK() {
        final DierDto dierDto = new DierDto();
        dierDto.setNaam(NAAM);
        dierDto.setSoort(DierSoort.EEND);
        dierDto.setVerblijfType(VerblijfType.GEVOGELTE);


        Verblijf verblijf = createVerblijfEntity(VerblijfType.GEVOGELTE, 4);
        Set<Dier> dierSet = new HashSet<>();
        dierSet.add(createDierEntity("Kwik", verblijf));
        dierSet.add(createDierEntity("Kwek", verblijf));
        verblijf.setDieren(dierSet);

        List<Verblijf> verblijfList = new ArrayList<>();
        verblijfList.add(verblijf);

        when(verblijfDao.findVerblijfEntitiesByVerblijfType(VerblijfType.GEVOGELTE)).thenReturn(Optional.of(verblijfList));
        cut.createDier(dierDto);
//        verify(dierDao).saveDier(dierEntityArgumentCaptor.capture());
//        DierEntity result = dierEntityArgumentCaptor.getValue();
//        assertThat(result.getNaam(), is(NAAM));
    }

    private Verblijf createVerblijfEntity(VerblijfType verblijfType, int capaciteit) {
        Verblijf verblijf = new Verblijf();
        verblijf.setVerblijfType(verblijfType);
        verblijf.setCapaciteit(capaciteit);
        return verblijf;
    }

    private Dier createDierEntity(String naam, Verblijf verblijf) {
        Dier dier = new Dier();
        dier.setNaam(naam);
        dier.setVerblijf(verblijf);
        return dier;
    }

}
