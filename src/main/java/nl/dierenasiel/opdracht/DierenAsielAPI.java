package nl.dierenasiel.opdracht;


import nl.dierenasiel.opdracht.core.Verblijf;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/verblijf")
public class DierenAsielAPI {


    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Verblijf> getVerblijven() {
        return em.createNamedQuery("Verblijf.findAll").getResultList();
    }

}
