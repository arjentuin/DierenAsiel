package nl.dierenasiel.opdracht.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DierenDto {

    private List<DierDto> content;

}
