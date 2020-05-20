package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.services.DierenAsielService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
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


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVerblijf(VerblijfDto verblijf) {
        dierenAsielService.createVerblijf(verblijf);
        return Response.ok(dierenAsielService.getVerblijven()).build();
    }
}
