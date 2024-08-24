package com.company.library.primary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/nakai")
    public String test() {
        log.info("this is test log message!");
        return "this is test message";
    }
}
