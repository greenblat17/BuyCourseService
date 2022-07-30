package com.greenblat.spring.controllers;

import com.greenblat.spring.models.Course;
import com.greenblat.spring.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.findAllCourse());
        model.addAttribute("new_course", new Course());
        return "index";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute("new_course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String getCourseInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", courseService.findOneCourse(id));
        return "show";
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }


}
