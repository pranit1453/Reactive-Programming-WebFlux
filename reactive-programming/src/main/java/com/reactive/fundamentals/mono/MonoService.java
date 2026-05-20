package com.reactive.fundamentals.mono;

public interface MonoService {

    void getDataInUnitMono();

    void getDataInMonoMap();

    void getDataInMultipleMonoWithZip();

    void getMonoWithMap();

    void getMonoWithFlatMap();

    void getFluxWithFlatMapMany();

    void getFluxFromMono() throws InterruptedException;
}
