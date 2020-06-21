package nl.dierenasiel.opdracht.dao;

import com.querydsl.jpa.impl.JPAQuery;
import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.entities.QDier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DierDsl {

    @PersistenceContext(name = "cursus")
    private EntityManager entityManager;

    public Dier getDierById(long dierId) {

        QDier dier = QDier.dier;
        JPAQuery<Dier> query = new JPAQuery<>(entityManager);

        Dier result = query.select(dier).from(dier).where(dier.id.eq(dierId)).fetchOne();
        return result;
    }

}
