package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class DierDto {

    private Long id;
    private String naam;
    private String soort;
    private LocalDateTime registratieDatum;
    private VerblijfDto verblijf;

}
