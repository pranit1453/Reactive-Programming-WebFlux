package com.reactive.fundamentals.mono;

import com.reactive.data.DataService;
import com.reactive.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

/**
 * Mono --->Publisher that have 0 or 1 items.
 */
@Service
@RequiredArgsConstructor
public class MonoServiceImpl implements MonoService {

    private final DataService dataService;


    @Override
    public void getDataInUnitMono() {

        Mono<Map<Integer, String>> unitMono = Mono
                .fromSupplier(() -> dataService.getData(101))
                .log()
                .flatMap(data ->
                        !data.isEmpty()
                                ? Mono.just(data)
                                : Mono.error(new BaseException("Data not available for key: " + 101)));
        unitMono.subscribe(System.out::println);

    }

    /**
     * just ---> i) immediate execution time
     * <p>
     * ii) Exception handling before mono
     * <p>
     * fromCallable ---> i) Wrap a blocking or synchronous piece of code into a Mono
     * so it can be executed reactively. (OnSubscription execution time)
     * <p>
     * ii) Exception handling inside reactive flow
     * <p>
     * iii) lazy loading good for DB calls
     */
    @Override
    public void getDataInMonoMap() {
        Set<Integer> keys = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Mono<Map<Integer, String>> monoMap = Mono.fromCallable(
                () -> dataService.getData(keys));
        monoMap.subscribe(data ->
                data.forEach((key, value) ->
                        System.out.println(key + " -> " + value)));
    }

    /**
     * Zip --->  used to combine the values from multiple Mono objects with Tuple
     * and also get data from per mono.
     */
    @Override
    public void getDataInMultipleMonoWithZip() {
        Mono<Map<Integer, String>> mono1 = Mono.just(dataService.getData(1));
        Mono<Map<Integer, String>> mono2 = Mono.just(dataService.getData(54));
        Mono.zip(mono1, mono2).subscribe(data ->
                System.out.println(data.getT1() + " -> " + data.getT2()));
    }

    /**
     * Map --->  used to transform the data from one type to another by applying a
     * synchronous function and return same item.
     */
    @Override
    public void getMonoWithMap() {
        Mono<String> mono = Mono.just("Hello, I'm Pranit");
        Mono<String> map = mono.map(String::toUpperCase);
        map.subscribe(System.out::println);
    }

    /**
     * FlatMap --->  used to transform the data from one type to another by applying
     * asynchronous function and return value emitted by the another mono.
     */
    @Override
    public void getMonoWithFlatMap() {
        Mono<String> mono1 = Mono.just("Learning reactive programming");
        Mono<String[]> annotherMono = mono1.flatMap(val1 -> Mono.just(val1.split(" ")));
        annotherMono.subscribe(data -> {
            for (String str : data) {
                System.out.println(str);
            }
        });
    }

    /**
     * FlatMapMany --->  used to transform the mono into flux and return flux.
     */
    @Override
    public void getFluxWithFlatMapMany() {
        Mono<String> mono = Mono.just("Java Spring boot Reactive programming");
        Flux<String> flux = mono.flatMapMany(value -> Flux.just(value.split(" ")));
        flux.subscribe(System.out::println);
    }

    /**
     * concatWith --->  used to combine the two mono and return flux
     */
    @Override
    public void getFluxFromMono() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Flux<String> flux = Mono
                .just("Hello")
                .concatWith(Mono.just("World"))
                .delayElements(Duration.ofMillis(2000));
        flux.subscribe(data -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(data);
        });
        Thread.sleep(4000);
    }


}
