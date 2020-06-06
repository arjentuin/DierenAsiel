package nl.dierenasiel.opdracht.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.dierenasiel.opdracht.enums.VerblijfType;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Verblijf", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Verblijf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "capaciteit")
    private Integer capaciteit;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VerblijfType verblijfType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "verblijf")
    private Set<Dier> dieren;

}
