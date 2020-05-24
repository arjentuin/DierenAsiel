package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class VerblijfDto {

    private Long id;
    private String naam;
    private List<DierDto> dieren;

}
