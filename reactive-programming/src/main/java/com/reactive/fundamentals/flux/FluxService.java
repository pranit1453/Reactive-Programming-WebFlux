package com.reactive.fundamentals.flux;

import reactor.core.publisher.Flux;

public interface FluxService {

    void getDataInFlux();

    void mapWithFlux();

    void filterWithFlux();

    Flux<Integer> filterRangeFlux();

    void flatMapWithFlux();

    void transformWithFlux();

    void defaultIfFlux();

    void switchIfFlux();

    void zipWithFlux();

    void sideEffectMethod();
}
