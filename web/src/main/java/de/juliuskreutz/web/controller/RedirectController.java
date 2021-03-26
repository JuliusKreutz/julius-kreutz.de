package de.juliuskreutz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    @GetMapping("/paypal")
    public RedirectView paypal() {
        return new RedirectView("https://www.paypal.com/paypalme/JuliusKreutz");
    }

}