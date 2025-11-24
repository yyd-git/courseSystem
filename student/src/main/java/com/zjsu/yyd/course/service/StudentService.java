package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.Student;
import com.zjsu.yyd.course.repository.EnrollmentRepository;
import com.zjsu.yyd.course.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(
            StudentRepository repository,
            EnrollmentRepository enrollmentRepository
    ) {
        this.repository = repository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /** 创建学生（学号唯一 + 邮箱校验） */
    @Transactional
    public Student createStudent(Student student) {

        // 1. 学号唯一
        repository.findByStudentId(student.getStudentId()).ifPresent(s -> {
            throw new RuntimeException("学号已存在");
        });

        // 2. 邮箱格式合法
        if (!isValidEmail(student.getEmail())) {
            throw new RuntimeException("邮箱格式不合法");
        }

        return repository.save(student);
    }

    /** 查询所有学生 */
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    /** 根据 ID 查学生 */
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));
    }

    /** 更新学生信息 */
    @Transactional
    public Student updateStudent(Long id, Student updated) {
        Student existing = getStudentById(id);

        // 检查学号是否被别人占用
        repository.findByStudentId(updated.getStudentId())
                .filter(s -> !s.getId().equals(id))
                .ifPresent(s -> {
                    throw new RuntimeException("学号已存在");
                });

        existing.setStudentId(updated.getStudentId());
        existing.setName(updated.getName());
        existing.setMajor(updated.getMajor());
        existing.setGrade(updated.getGrade());
        existing.setEmail(updated.getEmail());

        return repository.save(existing);
    }

    /** 删除学生（如果有选课记录禁止删除） */
    @Transactional
    public void deleteStudent(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));

        // 🔥 直接通过 EnrollmentRepository 查询，不用 RestTemplate
        boolean hasEnrollments = enrollmentRepository.existsById(student.getId());

        if (hasEnrollments) {
            throw new RuntimeException("学生有选课记录，无法删除");
        }

        repository.delete(student);
    }

    /** 工具：邮箱格式验证 */
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}
