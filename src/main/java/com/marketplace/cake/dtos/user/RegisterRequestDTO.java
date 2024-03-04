package com.marketplace.cake.dtos.user;

import com.marketplace.cake.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(
        @NotBlank
        String login,
        @NotBlank
        String password1,
        @NotBlank
        String password2,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotBlank(message = "Email é obrigatório.")
        String email,
        @NotBlank(message = "Cpf é obrigatório.")
        String cpf,
        @NotNull
        String phoneNumber,
        @NotNull
        UserRole role) {
}
