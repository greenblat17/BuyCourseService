package com.greenblat.spring.controllers;

import com.greenblat.spring.models.Course;
import com.greenblat.spring.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public String getAllCourses(@RequestParam(value = "name_course", required = false) String nameCourse,
                                Model model)  {
        model.addAttribute("courses", courseService.findAllCourse(nameCourse));
        model.addAttribute("new_course", new Course());
        return "courses/index";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute("new_course") Course course, @RequestParam("file") MultipartFile file) throws IOException {
        courseService.saveCourse(course, file);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String getCourseInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", courseService.findOneCourse(id));
        return "courses/show";
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }


}
