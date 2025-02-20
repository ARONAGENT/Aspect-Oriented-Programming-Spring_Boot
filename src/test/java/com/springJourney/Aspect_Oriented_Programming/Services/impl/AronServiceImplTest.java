package com.springJourney.Aspect_Oriented_Programming.Services.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AronServiceImplTest {

    @Autowired
    private AronServiceImpl aronService;

    @Test
    void aopOrderPackage() {

        String value=
                aronService.orderPackage(-34L);
        log.info(value);
    }

    @Test
    void aopTrackPackage() {
        String value=
                aronService.trackPackage(2L);
        log.info(value);
    }
}