package com.project.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class DemoController {

    @GetMapping("/product")
    public String getProduct() {
        return "product";
    }
}
