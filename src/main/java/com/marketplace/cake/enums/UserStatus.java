package com.marketplace.cake.enums;

public enum UserStatus {
    ACTIVE("active"),
    DELETED("deleted");

    private String status;

    UserStatus(String status) { this.status = status; }

    public String getStatus() { return status; }
}
