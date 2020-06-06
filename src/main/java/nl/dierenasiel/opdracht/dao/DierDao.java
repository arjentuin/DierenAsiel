package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.entities.Dier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface DierDao extends JpaRepository<Dier, Long> {

}
