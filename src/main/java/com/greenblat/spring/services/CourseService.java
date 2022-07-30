package com.greenblat.spring.services;

import com.greenblat.spring.models.Course;
import com.greenblat.spring.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course findOneCourse(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> findAllCourse(String nameCourse) {
        if (nameCourse != null)
            return courseRepository.findByNameCourse(nameCourse);
        return courseRepository.findAll();
    }

    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void updateCourse(int id, Course updatedCourse) {
        updatedCourse.setId(id);
        courseRepository.save(updatedCourse);
    }

    @Transactional
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}
