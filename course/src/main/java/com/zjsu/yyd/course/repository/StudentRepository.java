package com.zjsu.yyd.course.repository;

import com.zjsu.yyd.course.model.Student;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {
    private final Map<String, Student> students = new ConcurrentHashMap<>();

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(students.get(id));
    }

    public Optional<Student> findByStudentId(String studentId) {
        return students.values().stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
    }

    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public void deleteById(String id) {
        students.remove(id);
    }

    public boolean existsById(String id) {
        return students.containsKey(id);
    }
}
