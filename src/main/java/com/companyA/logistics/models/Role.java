package com.companyA.logistics.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {

    RIDER("RIDER"),
    ADMIN("ADMIN");

    public final String role;

    Role(String status){
        this.role = status;
    }

    @JsonValue
    public String getStatus() {
        return role;
    }
}
