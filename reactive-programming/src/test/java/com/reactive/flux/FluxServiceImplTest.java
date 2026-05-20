package com.reactive.flux;

import com.reactive.fundamentals.flux.FluxServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class FluxServiceImplTest {
    @Autowired
    private FluxServiceImpl fluxService;

    @Test
    void dataInFlux() {
        fluxService.getDataInFlux();
    }

    @Test
    void mapWithFlux() {
        fluxService.mapWithFlux();
    }

    @Test
    void filterWithFlux() {
        fluxService.filterWithFlux();
    }

    @Test
    void filterRangeFluxTest() {
        StepVerifier.create(fluxService.filterRangeFlux())
                .expectNext(2, 4, 6, 8, 10)
                .verifyComplete();
    }

    @Test
    void flatMapWithFlux() {
        fluxService.flatMapWithFlux();
    }

    @Test
    void transformWithFlux() {
        fluxService.transformWithFlux();
    }

    @Test
    void defaultIfFlux() {
        fluxService.defaultIfFlux();
    }

    @Test
    void switchIfFlux() {
        fluxService.switchIfFlux();
    }

    @Test
    void zipWithFlux() {
        fluxService.zipWithFlux();
    }

    @Test
    void sideEffectMethod() {
        fluxService.sideEffectMethod();
    }
}
