package nl.dierenasiel.opdracht.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity(name = "ForeignKeyAssoEntityVerblijf")
@Table(name = "Verblijf", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "naam")})
@NamedQuery(name = "Verblijf.findAll", query = "SELECT f FROM ForeignKeyAssoEntityVerblijf f")
public class VerblijfEntity implements Serializable {

    private static final long serialVersionUID = -1798070786993154656L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer verblijfId;

    @Column(name = "naam", nullable = false)
    private String naam;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "verblijf_id")
    private Set<DierEntity> dieren;

    public Integer getVerblijfId() {
        return verblijfId;
    }

    public void setVerblijfId(Integer verblijfId) {
        this.verblijfId = verblijfId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Set<DierEntity> getDieren() {
        return dieren;
    }

    public void setDieren(Set<DierEntity> dieren) {
        this.dieren = dieren;
    }
}
