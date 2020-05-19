package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.entity.DierEntity;
import nl.dierenasiel.opdracht.entity.VerblijfEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/dier")
public class DierEndpoint {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DierEntity> getDier() {
        return em.createNamedQuery("Dier.findAll").getResultList();
    }
}
