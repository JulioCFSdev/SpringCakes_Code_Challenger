package com.marketplace.cake.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
