package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.dierenasiel.opdracht.enums.VerblijfType;

import java.util.List;

@NoArgsConstructor
@Data
public class VerblijfDto {

    private Long id;
    private String naam;
    private List<DierDto> dieren;
    private Integer capaciteit;
    private VerblijfType verblijfType;
}
