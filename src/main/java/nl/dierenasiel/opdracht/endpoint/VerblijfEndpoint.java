package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.services.DierenAsielService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/verblijf")
public class VerblijfEndpoint {

    private DierenAsielService dierenAsielService;

    public VerblijfEndpoint() {
    }

    @Inject()
    public VerblijfEndpoint(DierenAsielService dierenAsielService) {
        this.dierenAsielService = dierenAsielService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerblijven() {
        return Response.ok(dierenAsielService.getVerblijven()).build();
    }

}
