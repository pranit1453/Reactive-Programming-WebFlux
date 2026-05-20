package com.reactive.dto;

public record UpdateBookRequest(
        String name,
        String description,
        String publisher,
        String author
) {
}
