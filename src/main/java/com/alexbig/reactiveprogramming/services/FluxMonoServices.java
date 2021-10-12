package com.alexbig.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class FluxMonoServices {

    public static final List<String> STRING_LIST = List.of("Apple", "Banana", "Orange");
    public static final Flux<String> FRUITS = Flux.just("Mango", "Orange");
    public static final Flux<String> VEGGIES = Flux.just("Cabbage", "Carrot");
    public static final Mono<String> MANGO = Mono.just("Mango");

    public Flux<String> fruitsFlux(){
        return Flux.fromIterable(STRING_LIST);
    }

    public Flux<String> fruitsFluxMap(){
        return Flux.fromIterable(STRING_LIST)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFilter(int number){
        return Flux.fromIterable(STRING_LIST)
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
                .delayElements(Duration.ofMillis(new Random().nextInt(100)))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap(){
        return Flux.fromIterable(STRING_LIST)
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(100)))
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
        return Flux.concat(FRUITS, VEGGIES);
    }

    public Flux<String> fruitsFluxConcatWithOperator(){
        return FRUITS.concatWith(VEGGIES);
    }

    public Flux<String> fruitsFluxMerge(){
        var fruits = FRUITS.delayElements(Duration.ofMillis(50));
        var veggies = VEGGIES.delayElements(Duration.ofMillis(70));
        return Flux.merge(fruits, veggies)
                .log();
    }

    public Flux<String> fruitsFluxMergeWith(){
        var fruits = FRUITS.delayElements(Duration.ofMillis(50));
        var veggies = VEGGIES.delayElements(Duration.ofMillis(70));
        return fruits.mergeWith(veggies)
                .log();
    }

    public Flux<String> fruitsFluxMergeWithSequential(){
        var fruits = FRUITS.delayElements(Duration.ofMillis(50));
        var veggies = VEGGIES.delayElements(Duration.ofMillis(70));
        return Flux.mergeSequential(fruits, veggies)
                .log();
    }

    public Flux<String> fruitsFluxZip(){
        return Flux.zip(FRUITS, VEGGIES, (s1, s2) -> s1+s2)
                .log();
    }

    public Flux<String> fruitsFluxZipWith(){
        return FRUITS.zipWith(VEGGIES, (s1, s2) -> s1+s2)
                .log();
    }

    public Flux<String> fruitsFluxZipTuple(){
        var moreVeggies = Flux.just("Lettuce", "Spinach");
        return Flux.zip(FRUITS, VEGGIES, moreVeggies)
                .map(objects -> objects.getT1()+objects.getT2()+objects.getT3())
                .log();
    }

    public Flux<String> fruitsFluxFilterDoOn(int number){
        return Flux.fromIterable(STRING_LIST)
                .filter(s -> s.length() > number)
                .doOnNext(s -> System.out.println("DoOn = "+ s))
                .doOnSubscribe(subscription -> System.out.println("Subscription = "+ subscription.toString()))
                .doOnComplete(() -> System.out.println("Completed"))
                .log();
    }

    public Mono<String> fruitMono(){
        return MANGO;
    }

    public Mono<List<String>> fruitsMonoFlatMap(){
        return MANGO
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<List<String>> fruitsMonoFlatMapMany(){
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(List.of(s.split(""))))
                .log();
    }

    public Mono<String> fruitsMonoZipWith(){
        var carrot = Mono.just("Carrot");
        return MANGO.zipWith(carrot, (s1, s2) -> s1+s2)
                .log();
    }

    public static void main(String[] args){
        FluxMonoServices fluxMonoServices = new FluxMonoServices();
        fluxMonoServices.fruitsFlux().subscribe(s -> System.out.println("Name = " + s));
        fluxMonoServices.fruitMono().subscribe(s -> System.out.println("Mono = " + s));
        fluxMonoServices.fruitsFluxFlatMap().subscribe(s -> System.out.println("FlatMap = " +s));
    }
}
