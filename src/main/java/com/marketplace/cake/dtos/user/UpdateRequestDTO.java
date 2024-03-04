package com.marketplace.cake.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateRequestDTO(
        @NotBlank
        String id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String email,
        @NotBlank
        String cpf,
        @NotBlank
        String phoneNumber
) {}
