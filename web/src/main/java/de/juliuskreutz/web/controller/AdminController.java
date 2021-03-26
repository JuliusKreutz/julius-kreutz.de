package de.juliuskreutz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("fileSavePath", PropertiesController.getFileSavePath());
        model.addAttribute("maxFileSize", PropertiesController.getMaxFileSize());
        return "admin";
    }

    @PostMapping("/admin")
    public String save(@RequestParam String fileSavePath, @RequestParam int maxFileSize, Model model) {
        PropertiesController.storeFileSavePath(fileSavePath);
        PropertiesController.storeMaxFileSize(maxFileSize);
        model.addAttribute("fileSavePath", PropertiesController.getFileSavePath());
        model.addAttribute("maxFileSize", PropertiesController.getMaxFileSize());
        return "admin";
    }

}
