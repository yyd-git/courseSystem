package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.Student;
import com.zjsu.yyd.course.repository.EnrollmentRepository;
import com.zjsu.yyd.course.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final EnrollmentRepository enrollmentRepo;
    public StudentService(StudentRepository repository, EnrollmentRepository enrollmentRepo) {
        this.repository = repository;
        this.enrollmentRepo = enrollmentRepo;
    }

    public Student createStudent(Student student) {
        // 检查学号唯一性
        repository.findByStudentId(student.getStudentId()).ifPresent(s -> {
            throw new RuntimeException("学号已存在");
        });
        if (!isValidEmail(student.getEmail())) {
            throw new RuntimeException("Invalid email format");
        }
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public Student updateStudent(String id, Student updated) {
        Student existing = getStudentById(id);

        // 检查学号是否与其他学生重复
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

    public void deleteStudent(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found");
        }
        boolean hasCourse = enrollmentRepo.existsByStudentId(id);
        if (hasCourse) {
            throw new RuntimeException("Student cannot be deleted because there are course selections");
        }
        repository.deleteById(id);
    }


    private boolean isValidEmail(String email) {
        // 使用简单正则匹配
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}
