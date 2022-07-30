package com.greenblat.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {

    @GetMapping("/")
    public String getAllCourses() {
        return "show";
    }
}
