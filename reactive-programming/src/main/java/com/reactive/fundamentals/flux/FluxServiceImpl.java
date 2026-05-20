package com.reactive.fundamentals.flux;

import com.reactive.data.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Flux --->Publisher that have 0 ..... n items.
 */
@Service
@RequiredArgsConstructor
public class FluxServiceImpl implements FluxService {

    private final DataService dataService;
    Function<Flux<String>, Flux<String>> func1 = (name) -> name.map(String::toUpperCase);
    Function<Flux<String>, Flux<String>> func2 = (name) -> name.filter(n -> n.length() <= 3);

    /**
     * in flux each item is processed one by one.
     */
    @Override
    public void getDataInFlux() {
        Flux<String> flux = Flux.just("Pranit", "Pratik", "Ram")
                .log();

        flux.subscribe(data -> {
            System.out.println(data);
            System.out.println("Complete one set");
        });
        Set<Integer> keys = Set.of(1, 2, 3, 4, 5);
        Flux<Map.Entry<Integer, String>> entryFlux = Flux.fromIterable(dataService.getData(keys).entrySet()).log();
        entryFlux.subscribe(data -> {
            System.out.println(data);
            System.out.println("Complete one ");
        });
    }

    /**
     * Map --->  used to transform the data from one type to another by applying a
     * synchronous function and return same item but one by one.
     */
    @Override
    public void mapWithFlux() {
        Flux.just("Pranit", "Pratik", "Ram")
                .map(String::toUpperCase)
                .log()
                .subscribe(data -> {
                    System.out.println(data);
                    System.out.println("Complete one set");
                });

    }

    @Override
    public void filterWithFlux() {
        Flux.just("Pranit", "Pratik", "Ram", "Rakesh")
                .filter(name -> name.length() <= 3)
                .log()
                .subscribe(data -> {
                    System.out.println(data);
                    System.out.println("Complete one set");
                });

    }

    @Override
    public Flux<Integer> filterRangeFlux() {

        return Flux.range(1, 10)
                .filter(i -> i % 2 == 0);
    }

    /**
     * FlatMap --->  used to transform the data from one type to another by applying
     * asynchronous function and return value emitted by the another flux but in stream.
     * <p>
     * concatMap ---> it preserve order of items.
     */
    @Override
    public void flatMapWithFlux() {
        Flux.just("Pranit", "Pratik")
                .flatMap(name -> Flux.just(name.split("")))
                .delayElements(Duration.ofMillis(2000))
                .log()
                .subscribe(data -> {
                    System.out.println(data);
                    System.out.println("Complete one set");
                });
    }

    /**
     * Transform --->  used to transform the data from one type to another by applying
     * functional interface.
     */
    @Override
    public void transformWithFlux() {
        Flux.just("Pranit", "Anil")
                .transform(func1)
                .log()
                .subscribe(data -> {
                    System.out.println(data);
                    System.out.println("Complete one set");
                });
    }

    @Override
    public void defaultIfFlux() {
        Flux.just("Pranit", "Anil", "Akash")
                .transform(func2)
                .defaultIfEmpty("No name available")
                .log()
                .subscribe(data -> {
                    System.out.println(data);
                    System.out.println("Complete one set");
                });
    }

    /**
     * Synchronous --
     * concat --> static
     * concatWith --> instance
     * <p>
     * Asynchronous --
     * merge --> static
     * mergeWith --> instance
     * <p>
     * defaultIfEmpty --> return string
     * <p>
     * switchIfEmpty --> return flux
     */
    @Override
    public void switchIfFlux() {

        Flux.just("Pranit", "Anil", "Akash")
                .transform(func2)
                .switchIfEmpty(Flux.just("Ram", "Rakesh")
                        .transform(func1))
                .log()
                .subscribe(data -> {
                    System.out.println(data);
                    System.out.println("Complete one set");
                });
    }

    @Override
    public void zipWithFlux() {
        Flux<Tuple2<Integer, String>> zip = Flux.zip(Flux.just(1, 2), Flux.just("Pranit", "Anil"));
        zip.subscribe(data -> {
            System.out.println(data);
            System.out.println("Complete one set");
        });
    }

    @Override
    public void sideEffectMethod() {
        Flux.just("Pranit", "Pratik", "Ram")
                .doOnRequest(request -> System.out.println(request + " on Request"))
                .doOnNext(name -> {
                    System.out.println(name + " on Next");
                })
                .doOnSubscribe(name -> {
                    System.out.println(name + " on Subscribe");
                })
                .doOnComplete(() -> System.out.println("on Complete"))
                .doOnError(error -> System.out.println("on Error"))
                .doOnEach(System.out::println)
                .subscribe(System.out::println);
    }

}
