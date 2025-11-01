package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.model.Enrollment;
import com.zjsu.yyd.course.model.Student;
import com.zjsu.yyd.course.repository.CourseRepository;
import com.zjsu.yyd.course.repository.EnrollmentRepository;
import com.zjsu.yyd.course.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo,
                             StudentRepository studentRepo,
                             CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    /**
     * 学生选课（创建 Enrollment 记录）
     */
    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // 1️⃣ 检查学生是否存在
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));

        // 2️⃣ 检查课程是否存在
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("课程不存在"));

        // 3️⃣ 检查是否已选过该课程
        enrollmentRepo.findByStudentAndCourse(student, course)
                .ifPresent(e -> { throw new RuntimeException("该学生已选择此课程"); });

        // 4️⃣ 检查课程容量是否已满
        long enrolledCount = enrollmentRepo.countByCourse(course);
        if (enrolledCount >= course.getCapacity()) {
            throw new RuntimeException("课程人数已满");
        }

        // 5️⃣ 创建新的选课记录（Enrollment 关联对象，而不是 ID）
        Enrollment enrollment = new Enrollment(student, course);
        return enrollmentRepo.save(enrollment);
    }

    /**
     * 查询所有选课记录
     */
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    /**
     * 查询某个学生的所有选课记录
     */
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));
        return enrollmentRepo.findByStudent(student);
    }

    /**
     * 查询某门课程的所有选课记录
     */
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("课程不存在"));
        return enrollmentRepo.findByCourse(course);
    }

    /**
     * 退选课程（删除 Enrollment 记录）
     */
    @Transactional
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepo.existsById(id)) {
            throw new RuntimeException("选课记录不存在");
        }
        enrollmentRepo.deleteById(id);
    }
}
