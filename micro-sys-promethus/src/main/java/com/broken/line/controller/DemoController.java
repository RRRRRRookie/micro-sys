package com.broken.line.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author: wanjia1
 * @date: 2023/6/1
 */
@Slf4j
@RestController
public class DemoController {

    @GetMapping("/counter")
    public void counter() {
        log.info("into request");
        int num = (int) (Math.random() * 11);
        if (num <= 5) {
            log.warn("waring warning {}", LocalDateTime.now());
            final Counter counter = Metrics.counter("customize.count", "error", "count");
            counter.increment();
        }
    }
}
