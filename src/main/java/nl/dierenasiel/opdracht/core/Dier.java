package nl.dierenasiel.opdracht.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dier", cascade = CascadeType.ALL)
    private Set<DierVerblijf> dierVerblijfs;

    public Dier() {
    }

    public Dier(String soort, String naam,
                LocalDateTime registratieDatum,
                DierVerblijf... dierVerblijfs) {
        this.soort = soort;
        this.naam = naam;
        this.registratieDatum = registratieDatum;
        for (DierVerblijf dierVerblijf : dierVerblijfs) dierVerblijf.setDier(this);
        this.dierVerblijfs = Stream.of(dierVerblijfs).collect(Collectors.toSet());
    }

}
