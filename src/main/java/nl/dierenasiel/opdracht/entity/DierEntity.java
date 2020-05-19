package nl.dierenasiel.opdracht.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "ForeignKeyAssoDierEntity")
@Table(name = "Dier", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@NamedQuery(name = "Dier.findAll", query = "SELECT f FROM ForeignKeyAssoDierEntity f")
public class DierEntity implements Serializable {
    private static final long serialVersionUID = -6790693372846798581L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer dierId;

    @Column(name = "naam")
    private String naam;

    @Column(name = "soort")
    private String soort;

    @Column(name = "registratie_datum")
    private LocalDateTime registratieDatum;

    @JsonIgnore
    @ManyToOne
    private VerblijfEntity verblijf;

    public Integer getDierId() {
        return dierId;
    }

    public void setDierId(Integer dierId) {
        this.dierId = dierId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public LocalDateTime getRegistratieDatum() {
        return registratieDatum;
    }

    public void setRegistratieDatum(LocalDateTime registratieDatum) {
        this.registratieDatum = registratieDatum;
    }

    public VerblijfEntity getVerblijf() {
        return verblijf;
    }

    public void setVerblijf(VerblijfEntity verblijf) {
        this.verblijf = verblijf;
    }
}
