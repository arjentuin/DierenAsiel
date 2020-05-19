package nl.dierenasiel.opdracht.endpoint;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/employee")
public class EmployeeEndpoint {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeEndpoint> getVerblijf() {
        return em.createNamedQuery("Employee.findAll").getResultList();

    }
}
