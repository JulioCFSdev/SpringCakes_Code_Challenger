package com.marketplace.cake.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(
        @NotBlank
        String id,
        @NotBlank
        String token) {
}
