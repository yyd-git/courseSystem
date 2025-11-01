package com.zjsu.yyd.course.controller;

import com.zjsu.yyd.course.model.Enrollment;
import com.zjsu.yyd.course.service.EnrollmentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    private Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        map.put("message", "Success");
        map.put("data", data);
        return map;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> enrollStudent(@RequestParam Long studentId,
                                                             @RequestParam Long courseId) {
        Enrollment enrollment = service.enrollStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(success(enrollment));
    }

    @GetMapping
    public Map<String, Object> getAllEnrollments() {
        return success(service.getAllEnrollments());
    }

    @GetMapping("/student/{studentId}")
    public Map<String, Object> getByStudent(@PathVariable Long studentId) {
        return success(service.getEnrollmentsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public Map<String, Object> getByCourse(@PathVariable Long courseId) {
        return success(service.getEnrollmentsByCourse(courseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEnrollment(@PathVariable Long id) {
        service.deleteEnrollment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }
}
