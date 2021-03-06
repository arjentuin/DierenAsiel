package nl.dierenasiel.opdracht.dao;

import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.enums.DierSoort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;
import java.util.Optional;

@Eager
public interface DierDao extends JpaRepository<Dier, Long> {

    Optional<List<Dier>> findDiersByIsBeschikbaarAndSoortIn(int isBeschikbaar, List<DierSoort> dierSoortSet);

}
