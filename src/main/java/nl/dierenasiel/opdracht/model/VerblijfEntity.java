package nl.dierenasiel.opdracht.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "verblijf")
    private Set<DierEntity> dieren;

}
