package org.apirest.core.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof BusinessException be) {
            return Response.status(be.getStatus())
                .entity(new ErrorResponse(be.getMessage()))
                .build();
        }
        if (exception instanceof ConstraintViolationException cve) {
            String message = cve.getConstraintViolations().stream()
                .findFirst()
                .map(v -> v.getMessage())
                .orElse("Validation error");
            return Response.status(400)
                .entity(new ErrorResponse(message))
                .build();
        }
        return Response.status(500)
            .entity(new ErrorResponse("Internal server error"))
            .build();
    }

    record ErrorResponse(String message) {}
}
