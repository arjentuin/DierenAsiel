package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.dto.DierDto;
import nl.dierenasiel.opdracht.entities.Dier;
import nl.dierenasiel.opdracht.services.DierService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/dier")
public class DierEndpoint {

    private DierService dierService;

    public DierEndpoint() {
    }

    @Inject()
    public DierEndpoint(DierService dierService) {
        this.dierService = dierService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDieren() {
        return Response.ok(dierService.getDieren()).build();
    }

    @GET
    @Path("{dierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDier(@PathParam("dierId") final long dierId) throws Exception {
        return Response.ok(dierService.getDier(dierId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerDier(DierDto dier) {
        Dier savedDier = dierService.registerDier(dier);
        return Response.ok(dierService.getDierenWithGeinsteresseerden(savedDier)).build();
    }

    @PUT
    @Path("{dierId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDier(@PathParam("dierId") final long dierId, DierDto dier) {
        dierService.updateDier(dierId, dier);
        return Response.ok(dierService.getDieren()).build();
    }

    @DELETE
    @Path("{dierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDier(@PathParam("dierId") final long dierId) {
        dierService.deleteDier(dierId);
        return Response.noContent().build();
    }

    @PUT
    @Path("{dierId}/adopt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adoptDier(@PathParam("dierId") final long dierId) {
        dierService.adoptDier(dierId);
        return Response.ok(dierService.getDieren()).build();
    }

}
