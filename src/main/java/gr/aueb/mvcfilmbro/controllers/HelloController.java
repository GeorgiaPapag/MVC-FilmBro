package gr.aueb.mvcfilmbro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "index"; // Name of the HTML file in src/main/resources/templates
    }
}

