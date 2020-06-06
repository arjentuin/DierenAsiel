package nl.dierenasiel.opdracht.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Persoon", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Persoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "emailadres")
    private String emailadres;

    @Column(name = "registratie_datum")
    private LocalDateTime registratieDatum;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "persoon")
    private Set<Interesse> interesse;
}
