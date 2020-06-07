package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.dto.VerblijfDto;
import nl.dierenasiel.opdracht.services.VerblijfService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/verblijf")
public class VerblijfEndpoint {

    private VerblijfService verblijfService;

    public VerblijfEndpoint() {
    }

    @Inject()
    public VerblijfEndpoint(VerblijfService verblijfService) {
        this.verblijfService = verblijfService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerblijven() {
        return Response.ok(verblijfService.getVerblijven()).build();
    }

    @GET
    @Path("{verblijfId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerblijf(@PathParam("verblijfId") final long verblijfId) {
        return Response.ok(verblijfService.getVerblijf(verblijfId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVerblijf(VerblijfDto verblijf) {
        verblijfService.createVerblijf(verblijf);
        return Response.ok(verblijfService.getVerblijven()).build();
    }

    @PUT
    @Path("{verblijfId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateVerblijf(@PathParam("verblijfId") final long verblijfId, VerblijfDto verblijfDto) {
        verblijfService.updateVerblijf(verblijfId, verblijfDto);
        return Response.ok(verblijfService.getVerblijven()).build();
    }

    @DELETE
    @Path("{verblijfId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVerblijf(@PathParam("verblijfId") final long verblijfId) {
        verblijfService.deleteVerblijf(verblijfId);
        return Response.noContent().build();
    }
}
