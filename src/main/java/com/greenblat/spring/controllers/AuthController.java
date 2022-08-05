package com.greenblat.spring.controllers;

import com.greenblat.spring.models.Person;
import com.greenblat.spring.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }


    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "security";
    }
}
