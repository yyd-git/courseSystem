package com.zjsu.yyd.course.repository;

import com.zjsu.yyd.course.model.Enrollment;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EnrollmentRepository {

    private final Map<String, Enrollment> enrollments = new ConcurrentHashMap<>();

    public Enrollment save(Enrollment enrollment) {
        enrollments.put(enrollment.getId(), enrollment);
        return enrollment;
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments.values());
    }

    public Optional<Enrollment> findById(String id) {
        return Optional.ofNullable(enrollments.get(id));
    }

    public List<Enrollment> findByStudentId(String studentId) {
        return enrollments.values().stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public List<Enrollment> findByCourseId(String courseId) {
        return enrollments.values().stream()
                .filter(e -> e.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    public Optional<Enrollment> findByStudentAndCourse(String studentId, String courseId) {
        return enrollments.values().stream()
                .filter(e -> e.getStudentId().equals(studentId) && e.getCourseId().equals(courseId))
                .findFirst();
    }

    public void deleteById(String id) {
        enrollments.remove(id);
    }

    public boolean existsById(String id) {
        return enrollments.containsKey(id);
    }

    public boolean existsByStudentId(String studentId) {
        return enrollments.values().stream()
                .anyMatch(e -> e.getStudentId().equals(studentId));
    }
}
