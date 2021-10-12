package com.alexbig.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxMonoServicesTest {

    FluxMonoServices fluxMonoServices = new FluxMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxMonoServices.fruitsFlux();
        var values = FluxMonoServices.STRING_LIST;
        StepVerifier.create(fruitsFlux)
                .thenConsumeWhile(s -> {
                    assertTrue(values.contains(s));
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitsMono = fluxMonoServices.fruitMono();
        StepVerifier.create(fruitsMono)
                .expectNext("Mango")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFlux = fluxMonoServices.fruitsFluxMap();
        var values = FluxMonoServices.STRING_LIST;
        StepVerifier.create(fruitsFlux)
                .thenConsumeWhile(s -> {
                    assertNotNull(values.stream().filter(s1 -> s1.equalsIgnoreCase(s)).findFirst().orElse(null));
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitFlux = fluxMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(fruitFlux)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitFlux = fluxMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(fruitFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitFlux = fluxMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitMono = fluxMonoServices.fruitsMonoFlatMap();
        StepVerifier.create(fruitMono).thenConsumeWhile(strings ->  {
            assertEquals(strings.size(), 5);
            return true;
        }).verifyComplete();
    }
}