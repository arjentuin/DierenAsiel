package nl.dierenasiel.opdracht.core;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Verblijf implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "verblijf", cascade = CascadeType.ALL)
    private Set<DierVerblijf> dierVerblijfs = new HashSet<>();

    public Verblijf() {

    }

    public Verblijf(String naam) {
        this.naam = naam;
    }


}
