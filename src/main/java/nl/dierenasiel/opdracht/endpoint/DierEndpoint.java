package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.services.DierenAsielService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/dier")
public class DierEndpoint {

    private DierenAsielService dierenAsielService;

    public DierEndpoint() {
    }

    @Inject()
    public DierEndpoint(DierenAsielService dierenAsielService) {
        this.dierenAsielService = dierenAsielService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDieren() {
        return Response.ok(dierenAsielService.getDieren()).build();
    }

    @GET
    @Path("{dierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDier(@PathParam("dierId") final long dierId) {
        return Response.ok(dierenAsielService.getDier(dierId)).build();
    }
}
