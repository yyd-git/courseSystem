package com.zjsu.yyd.course.controller;

import com.zjsu.yyd.course.model.Student;
import com.zjsu.yyd.course.service.StudentService;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        map.put("message", "Success");
        map.put("data", data);
        return map;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@Validated @RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(success(created));
    }

    @GetMapping
    public Map<String, Object> getAllStudents() {
        return success(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public Map<String, Object> getStudentById(@PathVariable String id) {
        return success(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateStudent(@PathVariable String id, @Validated @RequestBody Student student) {
        return success(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }
}
