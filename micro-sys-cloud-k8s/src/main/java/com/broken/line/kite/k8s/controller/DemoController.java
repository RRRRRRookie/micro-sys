package com.broken.line.kite.k8s.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wanjia1
 * @date: 2023/8/21
 */
@Slf4j
@RequestMapping("/testing")
@RestController
public class DemoController {

    @GetMapping("/demo")
    public void demo(@RequestParam(required = false) String param) {
        log.info(param);
    }


}
