package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminSigninController {

    @GetMapping("/admin/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/admin")
    public String redirectToIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/admin/contacts";
        }
        return "redirect:/admin/signin";
    }
}