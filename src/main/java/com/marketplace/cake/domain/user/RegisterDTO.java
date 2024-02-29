package com.marketplace.cake.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
