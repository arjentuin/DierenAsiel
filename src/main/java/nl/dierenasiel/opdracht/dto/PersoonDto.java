package nl.dierenasiel.opdracht.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.dierenasiel.opdracht.enums.DierSoort;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PersoonDto {

    private String emailadres;
    private String naam;
    private LocalDateTime registratieDatum;
    private List<DierSoort> soort;
    
}
