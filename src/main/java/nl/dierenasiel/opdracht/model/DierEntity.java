package nl.dierenasiel.opdracht.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Dier")
@Table(name = "Dier", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@NamedQueries({
        @NamedQuery(name = "Dier.findAll", query = "SELECT f FROM Dier f"),
        @NamedQuery(name = "Dier.findById", query = "SELECT f FROM Dier f WHERE f.id = :id")
})

public class DierEntity implements Serializable {
    private static final long serialVersionUID = -6790693372846798581L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "soort")
    private String soort;

    @Column(name = "registratie_datum")
    private LocalDateTime registratieDatum;

    @ManyToOne(fetch = FetchType.EAGER)
    private VerblijfEntity verblijf;

}
