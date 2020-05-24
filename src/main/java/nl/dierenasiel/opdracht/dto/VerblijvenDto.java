package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class VerblijvenDto {

    private List<VerblijfDto> content;
}
