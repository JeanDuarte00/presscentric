package com.presscentric.assessement.domain.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

public record User(
        @Id UUID id,
        @NotBlank String name,
        @Indexed String email
) {

    public User(String name, String email){
        this(UUID.randomUUID(), name, email);
    }

}
