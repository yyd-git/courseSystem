package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.*;
import com.zjsu.yyd.course.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // 学生选课
    public Enrollment enrollStudent(String studentId, String courseId) {
        // 检查学生存在
        studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));

        // 检查课程存在
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("课程不存在"));

        // 检查是否已选过
        enrollmentRepo.findByStudentAndCourse(studentId, courseId)
                .ifPresent(e -> { throw new RuntimeException("该学生已选择此课程"); });

        // 检查课程容量
        long count = enrollmentRepo.findByCourseId(courseId).size();
        if (count >= course.getCapacity()) {
            throw new RuntimeException("课程人数已满");
        }

        Enrollment enrollment = new Enrollment(studentId, courseId);
        return enrollmentRepo.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(String courseId) {
        return enrollmentRepo.findByCourseId(courseId);
    }

    public void deleteEnrollment(String id) {
        if (!enrollmentRepo.existsById(id)) {
            throw new RuntimeException("选课记录不存在");
        }
        enrollmentRepo.deleteById(id);
    }
}
