package com.presscentric.assessement.entrypoint.payload;

public record CreateUserInput(
        String name,
        String email
) {
}
