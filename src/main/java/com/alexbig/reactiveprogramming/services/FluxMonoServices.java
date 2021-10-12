package com.alexbig.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


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

    public Flux<String> fruitsFluxConcatMap(){
        return Flux.fromIterable(STRING_LIST)
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number){
        Function<Flux<String>, Flux<String>> filterData = stringFlux -> stringFlux.filter(s-> s.length() > number);
        return Flux.fromIterable(STRING_LIST)
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number){
        Function<Flux<String>, Flux<String>> filterData = stringFlux -> stringFlux.filter(s-> s.length() > number);
        return Flux.fromIterable(STRING_LIST)
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number){
        Function<Flux<String>, Flux<String>> filterData = stringFlux -> stringFlux.filter(s-> s.length() > number);
        return Flux.fromIterable(STRING_LIST)
                .transform(filterData)
                .switchIfEmpty(Flux.just("Default-1","Default-2"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxConcat(){
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Cabbage", "Carrot");
        return Flux.concat(fruits, veggies);
    }

    public Flux<String> fruitsFluxConcatWithOperator(){
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Cabbage", "Carrot");
        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsFluxMerge(){
        var fruits = Flux.just("Mango", "Orange").delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Cabbage", "Carrot").delayElements(Duration.ofMillis(70));
        return Flux.merge(fruits, veggies).log();
    }

    public Flux<String> fruitsFluxMergeWith(){
        var fruits = Flux.just("Mango", "Orange").delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Cabbage", "Carrot").delayElements(Duration.ofMillis(70));
        return fruits.mergeWith(veggies).log();
    }

    public Flux<String> fruitsFluxMergeWithSequential(){
        var fruits = Flux.just("Mango", "Orange").delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Cabbage", "Carrot").delayElements(Duration.ofMillis(70));
        return Flux.mergeSequential(fruits, veggies).log();
    }

    public Mono<String> fruitMono(){
        return Mono.just("Mango");
    }

    public Mono<List<String>> fruitsMonoFlatMap(){
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<List<String>> fruitsMonoFlatMapMany(){
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(List.of(s.split(""))))
                .log();
    }



    public static void main(String[] args){
        FluxMonoServices fluxMonoServices = new FluxMonoServices();
        fluxMonoServices.fruitsFlux().subscribe(s -> System.out.println("Name = " + s));
        fluxMonoServices.fruitMono().subscribe(s -> System.out.println("Mono = " + s));
        fluxMonoServices.fruitsFluxFlatMap().subscribe(s -> System.out.println("FlatMap = " +s));
    }
}
