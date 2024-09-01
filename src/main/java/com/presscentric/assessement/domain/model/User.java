package com.presscentric.assessement.domain.model;

import java.util.UUID;

public record User(
        UUID id,
        String name,
        String email
) {

    public User(String name, String email){
        this(UUID.randomUUID(), name, email);
    }

}
