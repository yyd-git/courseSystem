package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course updated) {
        Course existing = getCourseById(id);
        existing.setCode(updated.getCode());
        existing.setTitle(updated.getTitle());
        existing.setInstructor(updated.getInstructor());
        existing.setSchedule(updated.getSchedule());
        existing.setCapacity(updated.getCapacity());
        return courseRepository.save(existing);
    }

    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }
        courseRepository.deleteById(id);
    }
}
