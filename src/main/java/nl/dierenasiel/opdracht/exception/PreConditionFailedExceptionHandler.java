package nl.dierenasiel.opdracht.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PreConditionFailedExceptionHandler implements ExceptionMapper<PreConditionFailedException> {
    @Override
    public Response toResponse(PreConditionFailedException e) {
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
    }
}
