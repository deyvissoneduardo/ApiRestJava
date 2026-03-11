package org.apirest.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.apirest.domain.dto.LoginRequest;
import org.apirest.domain.dto.LoginResponse;
import org.apirest.interfaces.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Authentication")
public class AuthController {

    @Inject
    UserService userService;

    @POST
    @Path("/login")
    @PermitAll
    @Operation(summary = "Login and get JWT token")
    public LoginResponse login(@Valid LoginRequest request) {
        return userService.login(request);
    }
}
