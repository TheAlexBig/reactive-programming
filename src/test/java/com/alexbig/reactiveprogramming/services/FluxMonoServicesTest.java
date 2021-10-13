package com.alexbig.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void fruitsFluxConcatMap() {
        var fruitFlux = fluxMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        var fruitFlux = fluxMonoServices.fruitsMonoFlatMapMany();
        StepVerifier.create(fruitFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitFlux = fluxMonoServices.fruitsFluxTransform(5);
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitFlux = fluxMonoServices.fruitsFluxTransformDefaultIfEmpty(100);
        StepVerifier.create(fruitFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitFlux = fluxMonoServices.fruitsFluxTransformSwitchIfEmpty(6);
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitFlux = fluxMonoServices.fruitsFluxConcat();
        StepVerifier.create(fruitFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWithOperator() {
        var fruitFlux = fluxMonoServices.fruitsFluxConcatWithOperator();
        StepVerifier.create(fruitFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitFlux = fluxMonoServices.fruitsFluxMerge();
        StepVerifier.create(fruitFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitFlux = fluxMonoServices.fruitsFluxMergeWith();
        StepVerifier.create(fruitFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitFlux = fluxMonoServices.fruitsFluxMergeWithSequential();
        StepVerifier.create(fruitFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitFlux = fluxMonoServices.fruitsFluxZip();
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitFlux = fluxMonoServices.fruitsFluxZipWith();
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitFlux = fluxMonoServices.fruitsFluxZipTuple();
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var stringMono = fluxMonoServices.fruitsMonoZipWith();
        StepVerifier.create(stringMono)
                .expectNext("MangoCarrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitFlux = fluxMonoServices.fruitsFluxFilterDoOn(5);
        StepVerifier.create(fruitFlux)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitFlux = fluxMonoServices.fruitsFluxOnErrorReturn();
        StepVerifier.create(fruitFlux)
                .expectNext("Mango", "Orange", "ErrorFound")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitFlux = fluxMonoServices.fruitsFluxOnErrorContinue();
        StepVerifier.create(fruitFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
        var fruitFlux = fluxMonoServices.fruitsFluxOnErrorMap();
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxDoOnError() {
        var fruitFlux = fluxMonoServices.fruitsFluxDoOnError();
        StepVerifier.create(fruitFlux)
                .expectNextCount(2)
                .expectError(RuntimeException.class)
                .verify();
    }
}