package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
public class VerblijfDto {

    private Long id;
    private String naam;
    private List<DierDto> dieren;

}
