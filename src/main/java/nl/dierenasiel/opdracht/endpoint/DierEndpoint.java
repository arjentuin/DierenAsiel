package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.core.Dier;

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
    public List<Dier> getDieren() {
        return em.createNamedQuery("Dier.findAll").getResultList();

    }
}
