package com.reactive.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateBookRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "Publisher is required")
        String publisher,
        @NotBlank(message = "Author is required")
        String author
) {
}
