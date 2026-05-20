package com.reactive.dto;

import lombok.Builder;

@Builder
public record BookResponse(
        Integer bookId,
        String name,
        String description,
        String publisher,
        String author
) {
}
