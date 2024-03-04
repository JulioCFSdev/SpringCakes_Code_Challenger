package com.marketplace.cake.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDTO(
        @NotBlank
        String id,
        @NotBlank
        String oldPassword,
        @NotBlank
        String newPassword1,
        @NotBlank
        String newPassword2
) {}
