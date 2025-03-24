package com.healthcare.demo;


import com.healthcare.validator.NotSpace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/")
    public String test() {
        return "25-03-23 default setting !";

    }

}
