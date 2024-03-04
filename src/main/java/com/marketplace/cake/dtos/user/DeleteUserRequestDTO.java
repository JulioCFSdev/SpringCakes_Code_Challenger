package com.marketplace.cake.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserRequestDTO(
        @NotBlank
        String id,
        @NotBlank
        String password)
{}
