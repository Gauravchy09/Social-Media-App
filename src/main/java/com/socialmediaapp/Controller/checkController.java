package com.socialmediaapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class checkController {

    @GetMapping("/check")
    public String healthCheck() {
        return "All Working...";
    }

    @GetMapping("/greet")
    public String greet() {
        return "hello, user";
    }

    @GetMapping("/greet/{name}")
    public String greetWithName(@PathVariable String name) {
        return "hello, "+name;
    }

    @GetMapping("/")
    public String defaultController() {
        return "O hello bro, Where are you trying to go ?";
    }
}
