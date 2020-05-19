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
public class VerblijvenDto {

    private List<VerblijfDto> content;
}
