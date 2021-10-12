package com.alexbig.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxMonoServicesTest {

    FluxMonoServices fluxMonoServices = new FluxMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxMonoServices.fruitsFlux();
        StepVerifier.create(fruitsFlux)
                .expectNext("Apple", "Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitsMono = fluxMonoServices.fruitMono();
        StepVerifier.create(fruitsMono).expectNext("Mango").verifyComplete();
    }
}