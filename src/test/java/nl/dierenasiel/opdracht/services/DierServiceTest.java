package nl.dierenasiel.opdracht.services;

import nl.dierenasiel.opdracht.dao.DierDao;
import nl.dierenasiel.opdracht.dao.VerblijfDao;
import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.enums.DierSoort;
import nl.dierenasiel.opdracht.enums.VerblijfType;
import nl.dierenasiel.opdracht.exception.PreConditionFailedException;
import nl.dierenasiel.opdracht.model.DierEntity;
import nl.dierenasiel.opdracht.model.VerblijfEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DierServiceTest {

    private final static String NAAM = "Kwek";

    @Mock
    private DierMapper dierMapper;

    @Mock
    private DierDao dierDao;

    @Mock
    private VerblijfDao verblijfDao;

    @Captor
    private ArgumentCaptor<DierEntity> dierEntityArgumentCaptor;

    @InjectMocks
    private DierService cut;

    @Test
    void testCreateDier_NoVerblijf() {
        final DierDto dierDto = new DierDto();
        dierDto.setNaam(NAAM);
        dierDto.setSoort(DierSoort.EEND);
        dierDto.setVerblijfType(VerblijfType.GEVOGELTE);

        when(verblijfDao.getVerblijfByType(VerblijfType.GEVOGELTE)).thenReturn(new ArrayList<>());

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


        VerblijfEntity verblijfEntity = createVerblijfEntity(VerblijfType.GEVOGELTE, 2);
        Set<DierEntity> dierEntitySet = new HashSet<>();
        dierEntitySet.add(createDierEntity("Kwik", verblijfEntity));
        dierEntitySet.add(createDierEntity("Kwek", verblijfEntity));
        verblijfEntity.setDieren(dierEntitySet);

        List<VerblijfEntity> verblijfEntityList = new ArrayList<>();
        verblijfEntityList.add(verblijfEntity);

        when(verblijfDao.getVerblijfByType(VerblijfType.GEVOGELTE)).thenReturn(verblijfEntityList);

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


        VerblijfEntity verblijfEntity = createVerblijfEntity(VerblijfType.GEVOGELTE, 4);
        Set<DierEntity> dierEntitySet = new HashSet<>();
        dierEntitySet.add(createDierEntity("Kwik", verblijfEntity));
        dierEntitySet.add(createDierEntity("Kwek", verblijfEntity));
        verblijfEntity.setDieren(dierEntitySet);

        List<VerblijfEntity> verblijfEntityList = new ArrayList<>();
        verblijfEntityList.add(verblijfEntity);

        when(verblijfDao.getVerblijfByType(VerblijfType.GEVOGELTE)).thenReturn(verblijfEntityList);
        cut.createDier(dierDto);
        verify(dierDao).saveDier(dierEntityArgumentCaptor.capture());
        DierEntity result = dierEntityArgumentCaptor.getValue();
        assertThat(result.getNaam(), is(NAAM));
    }

    private VerblijfEntity createVerblijfEntity(VerblijfType verblijfType, int capaciteit) {
        VerblijfEntity verblijfEntity = new VerblijfEntity();
        verblijfEntity.setVerblijfType(verblijfType);
        verblijfEntity.setCapaciteit(capaciteit);
        return verblijfEntity;
    }

    private DierEntity createDierEntity(String naam, VerblijfEntity verblijfEntity) {
        DierEntity dierEntity = new DierEntity();
        dierEntity.setNaam(naam);
        dierEntity.setVerblijf(verblijfEntity);
        return dierEntity;
    }

}
