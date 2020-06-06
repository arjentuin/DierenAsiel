package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.entities.Verblijf;
import nl.dierenasiel.opdracht.enums.VerblijfType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;
import java.util.Optional;

@Eager
public interface VerblijfDao extends JpaRepository<Verblijf, Long> {

    Optional<List<Verblijf>> findVerblijfEntitiesByVerblijfType(VerblijfType verblijfType);

}
