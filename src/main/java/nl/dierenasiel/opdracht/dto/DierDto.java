package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.dierenasiel.opdracht.enums.DierSoort;
import nl.dierenasiel.opdracht.enums.VerblijfType;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class DierDto {

    private Long id;
    private String naam;
    private DierSoort soort;
    private LocalDateTime registratieDatum;
    private VerblijfDto verblijf;
    private VerblijfType verblijfType;
    private List<PersoonDto> geinteresseerden;

}
