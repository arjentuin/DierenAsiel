package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.entities.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface PersoonDao extends JpaRepository<Persoon, Long> {
}
