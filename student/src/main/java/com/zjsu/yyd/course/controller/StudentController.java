package com.zjsu.yyd.course.controller;

import com.zjsu.yyd.course.model.Student;
import com.zjsu.yyd.course.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.zjsu.yyd.course.common.ApiResponse.success;

@RestController
@RequestMapping("/api/students")
@Tag(name = "学生管理接口", description = "学生的增删改查接口")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    private Map<String, Object> success(Object data) {
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("code", 200);
//        map.put("message", "Success");
//        map.put("data", data);
//        return map;
//    }

    @Operation(summary = "新增学生", description = "创建一个新的学生记录，需提供学号、姓名、邮箱等信息")
    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(
            @Parameter(description = "学生对象", required = true)
            @Validated @RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(success(created));
    }

    @Operation(summary = "获取所有学生", description = "返回数据库中所有学生记录")
    @GetMapping
    public Map<String, Object> getAllStudents() {
        return success(studentService.getAllStudents());
    }

    @Operation(summary = "根据ID查询学生", description = "根据学生ID查询具体学生信息")
    @GetMapping("/{id}")
    public Map<String, Object> getStudentById(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Long id) {
        return success(studentService.getStudentById(id));
    }

    @Operation(summary = "更新学生信息", description = "根据学生ID更新学生信息")
    @PutMapping("/{id}")
    public Map<String, Object> updateStudent(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "更新后的学生对象", required = true)
            @Validated @RequestBody Student student) {
        return success(studentService.updateStudent(id, student));
    }

    @Operation(summary = "删除学生", description = "根据学生ID删除学生，若有选课记录则删除失败")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }
}
