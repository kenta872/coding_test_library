package com.company.library.primary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/nakai")
    public String test() {
        return "this is test message";
    }
}
