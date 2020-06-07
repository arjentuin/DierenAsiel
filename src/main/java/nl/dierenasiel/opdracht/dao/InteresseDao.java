package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.entities.Interesse;
import nl.dierenasiel.opdracht.enums.DierSoort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;
import java.util.Optional;

@Eager
public interface InteresseDao extends JpaRepository<Interesse, Long> {

    Optional<List<Interesse>> findBySoort(DierSoort dierSoort);

}
