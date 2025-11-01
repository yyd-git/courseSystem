package com.zjsu.yyd.course.repository;

import com.zjsu.yyd.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 根据学号查找学生
    Optional<Student> findByStudentId(String studentId);

    // 判断学号是否存在
    boolean existsByStudentId(String studentId);


    // 按邮箱查找学生
    Student findByEmail(String email);

    // 判重检查（是否存在指定学号或邮箱）
    boolean existsByEmail(String email);

    // 按专业筛选
    List<Student> findByMajor(String major);

    // 按年级筛选
    List<Student> findByGrade(Integer grade);
}
