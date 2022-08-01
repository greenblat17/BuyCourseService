package com.greenblat.spring.services;

import com.greenblat.spring.models.Course;
import com.greenblat.spring.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    @Value("${upload.path}")
    private String uploadPath;

    private final CourseRepository courseRepository;

    public Course findOneCourse(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> findAllCourse(String nameCourse) {
        if (nameCourse != null)
            return courseRepository.findByNameCourseStartingWith(nameCourse);
        return courseRepository.findAll();
    }

    @Transactional
    public void saveCourse(Course course, MultipartFile file) throws IOException {

        // upload image
        if (file != null) {
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists())
                uploadFolder.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "-" + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            course.setImageName(resultFileName);
        }

        log.info("Saving new course with name {} and price {}", course.getNameCourse(), course.getPrice());
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
