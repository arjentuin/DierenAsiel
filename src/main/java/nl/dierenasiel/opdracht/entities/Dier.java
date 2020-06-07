package nl.dierenasiel.opdracht.entities;


import lombok.Getter;
import lombok.Setter;
import nl.dierenasiel.opdracht.enums.DierSoort;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Dier", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Dier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "soort")
    @Enumerated(EnumType.STRING)
    private DierSoort soort;

    @Column(name = "registratie_datum")
    private LocalDateTime registratieDatum;

    @Column(name = "isBeschikbaar", columnDefinition="tinyint(1) default 1")
    private int isBeschikbaar;

    @ManyToOne()
    private Verblijf verblijf;

}
