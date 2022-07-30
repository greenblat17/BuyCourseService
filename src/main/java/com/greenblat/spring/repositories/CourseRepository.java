package com.greenblat.spring.repositories;

import com.greenblat.spring.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByNameCourseStartingWith(String name);
}
