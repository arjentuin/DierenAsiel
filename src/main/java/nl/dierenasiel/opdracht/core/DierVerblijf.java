package nl.dierenasiel.opdracht.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class DierVerblijf implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @ManyToOne
    @JoinColumn
//    @JsonIgnore
    private Dier dier;

    @Id
    @ManyToOne
    @JoinColumn
//    @JsonIgnore
    private Verblijf verblijf;

    private Long aanwezig;

    public DierVerblijf(Verblijf verblijf, Long aanwezig) {
        this.verblijf = verblijf;
        this.aanwezig = aanwezig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DierVerblijf)) return false;
        DierVerblijf that = (DierVerblijf) o;
        return Objects.equals(dier.getNaam(), that.dier.getNaam()) &&
                Objects.equals(verblijf.getNaam(), that.verblijf.getNaam()) &&
                Objects.equals(aanwezig, that.aanwezig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dier.getNaam(), verblijf.getNaam(), aanwezig);
    }

}
