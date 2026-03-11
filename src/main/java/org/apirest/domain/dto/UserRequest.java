package org.apirest.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank @Size(min = 2, max = 255) String name,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6) String password
) {}
