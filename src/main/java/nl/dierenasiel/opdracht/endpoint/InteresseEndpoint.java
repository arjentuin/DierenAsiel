package nl.dierenasiel.opdracht.endpoint;

import nl.dierenasiel.opdracht.dto.PersoonDto;
import nl.dierenasiel.opdracht.services.DierService;
import nl.dierenasiel.opdracht.services.InteresseService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/interesse")
public class InteresseEndpoint {

    private InteresseService interesseService;

    private DierService dierService;

    public InteresseEndpoint() {
    }

    @Inject
    public InteresseEndpoint(InteresseService interesseService, DierService dierService) {
        this.interesseService = interesseService;
        this.dierService = dierService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGeinteresseerde(PersoonDto persoonDto) {
        interesseService.createGeinteresseerde(persoonDto);
        return Response.ok(dierService.getBeschikbareDierenInSoorten(persoonDto.getSoort())).build();
    }

}
