package nl.dierenasiel.opdracht.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.dierenasiel.opdracht.enums.VerblijfType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Verblijf")
@Table(name = "Verblijf", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries({
        @NamedQuery(name = "Verblijf.findAll", query = "SELECT f FROM Verblijf f"),
        @NamedQuery(name = "Verblijf.findById", query = "SELECT f FROM Verblijf f WHERE f.id = :id"),
        @NamedQuery(name = "Verblijf.findByVerblijfType", query = "SELECT f FROM Verblijf f WHERE f.verblijfType = :verblijfType"),
        @NamedQuery(name = "Verblijf.deleteById", query = "DELETE FROM Verblijf f WHERE f.id = :id")
})

public class VerblijfEntity implements Serializable {

    private static final long serialVersionUID = -1798070786993154656L;

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
    private Set<DierEntity> dieren;

}
