package com.zjsu.yyd.course.controller;

import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "课程管理接口", description = "提供课程的增删改查等功能")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "查询所有课程", description = "获取系统中所有课程列表")
    @GetMapping
    public Map<String, Object> getAllCourses() {
        return success(courseService.getAllCourses());
    }

    @Operation(summary = "根据课程ID查询课程")
    @GetMapping("/{id}")
    public Map<String, Object> getCourseById(
            @Parameter(description = "课程ID")
            @PathVariable Long id) {
        return success(courseService.getCourseById(id));
    }

    @Operation(summary = "创建课程", description = "创建一个新的课程信息")
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCourse(
            @Validated @RequestBody
            @Parameter(description = "课程实体信息") Course course) {

        Course saved = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(success(saved));
    }

    @Operation(summary = "更新课程信息")
    @PutMapping("/{id}")
    public Map<String, Object> updateCourse(
            @Parameter(description = "课程ID") @PathVariable Long id,
            @Validated @RequestBody
            @Parameter(description = "更新后的课程信息") Course updated) {

        return success(courseService.updateCourse(id, updated));
    }

    @Operation(summary = "删除课程", description = "删除指定课程，并通过选课微服务清理相关选课记录")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(
            @Parameter(description = "课程ID") @PathVariable Long id) {

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

    @Operation(summary = "根据课程代码查询课程")
    @GetMapping("/code/{code}")
    public Map<String, Object> getCourseByCode(
            @Parameter(description = "课程代码") @PathVariable String code) {
        return success(courseService.getCourseByCode(code));
    }

    @Operation(summary = "模糊搜索课程", description = "根据关键字搜索课程标题")
    @GetMapping("/search")
    public Map<String, Object> searchCourses(
            @Parameter(description = "搜索关键字") @RequestParam String keyword) {
        return success(courseService.searchCourses(keyword));
    }

    @Operation(summary = "查询有空位的课程", description = "调用选课服务获取实时人数，对比容量筛选可选课程")
    @GetMapping("/available")
    public Map<String, Object> getCoursesWithRemainingCapacity() {
        return success(courseService.getCoursesWithRemainingCapacity());
    }

}
