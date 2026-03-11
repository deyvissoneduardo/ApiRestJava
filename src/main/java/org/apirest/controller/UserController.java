package org.apirest.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.apirest.domain.dto.*;
import org.apirest.interfaces.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Users")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @PermitAll
    @Operation(summary = "Create user")
    public UserResponse create(@Valid UserRequest request) {
        return userService.create(request);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("user")
    @Operation(summary = "Find user by ID")
    public UserResponse findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "List all users")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("user")
    @Operation(summary = "Update user")
    public UserResponse update(@PathParam("id") Long id, @Valid UserRequest request) {
        return userService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("user")
    @Operation(summary = "Delete user")
    public void delete(@PathParam("id") Long id) {
        userService.delete(id);
    }
}
