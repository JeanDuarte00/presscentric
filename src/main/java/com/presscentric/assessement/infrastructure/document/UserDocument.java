package com.presscentric.assessement.infrastructure.document;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public record UserDocument(
        UUID id,
        String name,
        //@Indexed
        String email
) {}
