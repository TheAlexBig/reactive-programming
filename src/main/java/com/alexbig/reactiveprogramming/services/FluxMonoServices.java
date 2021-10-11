package com.alexbig.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxMonoServices {
    public Flux<String> fruitsFlux(){
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange")).log();
    }
    public Mono<String> fruitMono(){
        return Mono.just("Mango").log();
    }

    public static void main(String[] args){
        FluxMonoServices fluxMonoServices = new FluxMonoServices();
        fluxMonoServices.fruitsFlux().subscribe(s -> System.out.println("Name = " + s));
        fluxMonoServices.fruitMono().subscribe(s -> System.out.println("Mono = " + s));
    }
}
