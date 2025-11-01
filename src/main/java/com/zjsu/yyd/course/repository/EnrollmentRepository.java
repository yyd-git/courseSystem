package com.zjsu.yyd.course.repository;

import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.model.Enrollment;
import com.zjsu.yyd.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);
    long countByCourse(Course course);

    void deleteAllByCourseId(Long CourseId);




    /**  判断学生是否已选某门课 */
    boolean existsByCourseAndStudent(Course course, Student student);
}
