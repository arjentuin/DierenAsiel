package nl.dierenasiel.opdracht.entities;


import lombok.Getter;
import lombok.Setter;
import nl.dierenasiel.opdracht.enums.DierSoort;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Interesse", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;


    @Column(name = "soort")
    @Enumerated(EnumType.STRING)
    private DierSoort soort;

    @ManyToOne()
    private Persoon persoon;
}
