package com.reactive.wrapper;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ErrorResponse(
        String message,
        Instant timestamp
) {
}
