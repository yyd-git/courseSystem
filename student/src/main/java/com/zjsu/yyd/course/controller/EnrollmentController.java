package com.zjsu.yyd.course.controller;

import com.zjsu.yyd.course.model.Enrollment;
import com.zjsu.yyd.course.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.zjsu.yyd.course.common.ApiResponse.success;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "学生选课", description = "根据 studentId 和 courseId 创建一条选课记录")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "选课成功"),
            @ApiResponse(responseCode = "400", description = "参数有误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> enrollStudent(
            @Parameter(description = "学生 ID", required = true) @RequestParam Long studentId,
            @Parameter(description = "课程 ID", required = true) @RequestParam Long courseId) {

        Enrollment enrollment = service.enrollStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(success(enrollment));
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "查询全部选课记录", description = "返回所有学生的选课信息")
    @GetMapping
    public Map<String, Object> getAllEnrollments() {
        return success(service.getAllEnrollments());
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "根据学生查询选课记录", description = "通过 studentId 查询该学生所有选课")
    @GetMapping("/student/{studentId}")
    public Map<String, Object> getByStudent(
            @Parameter(description = "学生 ID", required = true) @PathVariable Long studentId) {
        return success(service.getEnrollmentsByStudent(studentId));
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "根据课程查询选课记录", description = "通过 courseId 返回该课程所有选课的学生信息")
    @GetMapping("/course/{courseId}")
    public Map<String, Object> getByCourse(
            @Parameter(description = "课程 ID", required = true) @PathVariable Long courseId) {
        return success(service.getEnrollmentsByCourse(courseId));
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "删除单条选课记录", description = "根据选课记录 ID 删除一条记录")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "记录不存在")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEnrollment(
            @Parameter(description = "选课记录 ID", required = true) @PathVariable Long id) {
        service.deleteEnrollment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "查询课程选课人数", description = "根据 courseId 返回该课程已被选课的人数")
    @GetMapping("/course/{courseId}/count")
    public Map<String, Object> getEnrollmentCount(
            @Parameter(description = "课程 ID", required = true) @PathVariable Long courseId) {

        int count = service.countByCourseId(courseId);
        return success(count);
    }

    // ----------------------------------------------------------------------

    @Operation(summary = "删除某课程所有选课记录", description = "用于在删除课程时同步清理对应选课数据（课程微服务会调用）")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "所有选课记录已清理")
    })
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Map<String, Object>> deleteByCourse(
            @Parameter(description = "课程 ID", required = true) @PathVariable Long courseId) {

        service.deleteByCourse(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }
}
