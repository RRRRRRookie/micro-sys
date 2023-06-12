package com.broken.line.controller;

import com.broken.line.component.CustomizeMetric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wanjia1
 * @date: 2023/6/1
 */
@Slf4j
@RestController
public class DemoController {

    /**
     * https://blog.csdn.net/qq_23056495/article/details/108010315 @Timed 使用方案
     */
    @CustomizeMetric("customize API")
    @GetMapping("/counter")
    public String counter() {
        log.info("{} into request", Thread.currentThread().getName());
        int num = (int) (Math.random() * 11);
        if (num > 3 && num <= 5) {
            throw new RuntimeException("exception here");
        }
        return String.valueOf(num);
    }
}
