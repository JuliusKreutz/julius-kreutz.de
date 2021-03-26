package de.juliuskreutz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/pgdp")
    public String pgdp() {
        return "tutor/pgdp";
    }

    @GetMapping("/gad")
    public String gad() {
        return "tutor/gad";
    }

    @GetMapping("/upload")
    public String upload() {
        return "other/upload";
    }
}
