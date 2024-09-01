package com.presscentric.assessement.entrypoint.payload;


public record UpdateUserInput(
        String name,
        String email
) {
}
