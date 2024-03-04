package com.marketplace.cake.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestRoutesController {
    @GetMapping
    public String getMappingTest(){
        return "Test is okay";
    }
}
