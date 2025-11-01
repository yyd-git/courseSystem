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
    private final EnrollmentRepository enrollmentRepo;

    public StudentService(StudentRepository repository, EnrollmentRepository enrollmentRepo) {
        this.repository = repository;
        this.enrollmentRepo = enrollmentRepo;
    }

    /**
     * 创建学生（含邮箱格式与学号唯一性检查）
     */
    @Transactional
    public Student createStudent(Student student) {
        // 1️⃣ 检查学号是否重复
        repository.findByStudentId(student.getStudentId()).ifPresent(s -> {
            throw new RuntimeException("学号已存在");
        });

        // 2️⃣ 检查邮箱格式
        if (!isValidEmail(student.getEmail())) {
            throw new RuntimeException("邮箱格式不合法");
        }

        // 3️⃣ 保存学生
        return repository.save(student);
    }

    /**
     * 查询所有学生
     */
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    /**
     * 根据ID查询学生
     */
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));
    }

    /**
     * 更新学生信息（含学号唯一性验证）
     */
    @Transactional
    public Student updateStudent(Long id, Student updated) {
        Student existing = getStudentById(id);

        // 检查学号是否被其他人占用
        repository.findByStudentId(updated.getStudentId())
                .filter(s -> !s.getId().equals(id))
                .ifPresent(s -> {
                    throw new RuntimeException("学号已存在");
                });

        // 更新字段
        existing.setStudentId(updated.getStudentId());
        existing.setName(updated.getName());
        existing.setMajor(updated.getMajor());
        existing.setGrade(updated.getGrade());
        existing.setEmail(updated.getEmail());

        return repository.save(existing);
    }

    /**
     * 删除学生（如果仍有关联选课记录则禁止删除）
     */
    @Transactional
    public void deleteStudent(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在"));

        // 判断该学生是否存在选课记录（JPA风格）
        boolean hasEnrollments = !enrollmentRepo.findByStudent(student).isEmpty();
        if (hasEnrollments) {
            throw new RuntimeException("学生有选课记录，无法删除");
        }

        repository.delete(student);
    }

    /**
     * 邮箱格式校验
     */
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}
