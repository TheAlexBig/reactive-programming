package com.alexbig.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxMonoServices {

    public static final List<String> STRING_LIST = List.of("Apple", "Banana", "Orange");

    public Flux<String> fruitsFlux(){
        return Flux.fromIterable(STRING_LIST);
    }

    public Flux<String> fruitsFluxMap(){
        return
                Flux.fromIterable(STRING_LIST)
                        .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFilter(int number){
        return
                Flux.fromIterable(STRING_LIST)
                        .filter(s -> s.length() > number);
    }

    public Flux<String> fruitsFluxFlatMap(){
        return Flux.fromIterable(STRING_LIST)
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(STRING_LIST)
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Mono<String> fruitMono(){
        return Mono.just("Mango");
    }

    public Mono<List<String>> fruitsMonoFlatMap(){
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public static void main(String[] args){
        FluxMonoServices fluxMonoServices = new FluxMonoServices();
        fluxMonoServices.fruitsFlux().subscribe(s -> System.out.println("Name = " + s));
        fluxMonoServices.fruitMono().subscribe(s -> System.out.println("Mono = " + s));
        fluxMonoServices.fruitsFluxFlatMap().subscribe(s -> System.out.println("FlatMap = " +s));
    }
}
