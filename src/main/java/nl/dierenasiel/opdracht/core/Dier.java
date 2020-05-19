package nl.dierenasiel.opdracht.core;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NamedQuery(name = "Dier.findAll", query = "SELECT d FROM Dier d")
public class Dier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "soort")
    private String soort;

    @Column(name = "naam")
    private String naam;

    @Column(name = "registratie_datum")
    private LocalDateTime registratieDatum;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dier", cascade = CascadeType.ALL)
//    private Set<Verblijf> verblijven = new HashSet<>();

    public Dier() {
    }

    public Dier(String soort, String naam,
                LocalDateTime registratieDatum,
                Set<Verblijf> verblijven) {
        this.soort = soort;
        this.naam = naam;
        this.registratieDatum = registratieDatum;
//        this.verblijven = verblijven;

    }

}
