package com.zjsu.yyd.course.controller;

import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.service.CourseService;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Map<String, Object> getAllCourses() {
        return success(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCourseById(@PathVariable Long id) {
        return success(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCourse(@Validated @RequestBody Course course) {
        Course saved = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(success(saved));
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateCourse(@PathVariable Long id, @Validated @RequestBody Course updated) {
        return success(courseService.updateCourse(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }

    private Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        map.put("message", "Success");
        map.put("data", data);
        return map;
    }

    @GetMapping("/{code}")
    public Map<String, Object> getCourseByCode(@PathVariable String code) {
        return success(courseService.getCourseByCode(code));
    }

    /** 模糊搜索课程 */
    @GetMapping("/search")
    public Map<String, Object> searchCourses(@RequestParam String keyword) {
        return success(courseService.searchCourses(keyword));
    }

    /** 查询有剩余容量的课程 */
    @GetMapping("/available")
    public Map<String, Object> getCoursesWithRemainingCapacity() {
        return success(courseService.getCoursesWithRemainingCapacity());
    }





}
