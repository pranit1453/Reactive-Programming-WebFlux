package com.reactive.mono;

import com.reactive.fundamentals.mono.MonoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonoServiceImplTest {

    @Autowired
    private MonoServiceImpl monoService;


    @Test
    void unitMonoTest() {
        monoService.getDataInUnitMono();
    }

    @Test
    void dataInMono() {
        monoService.getDataInMonoMap();
    }

    @Test
    void multipleMonoTest() {
        monoService.getDataInMultipleMonoWithZip();
    }

    @Test
    void mapMonoTest() {
        monoService.getMonoWithMap();
    }

    @Test
    void flatMapMonoTest() {
        monoService.getMonoWithFlatMap();
    }

    @Test
    void flatMapManyMonoTest() {
        monoService.getFluxWithFlatMapMany();
    }

    @Test
    void concatWithMonoTest() throws InterruptedException {
        monoService.getFluxFromMono();
    }

}
