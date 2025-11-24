package com.zjsu.yyd.course.repository;
import com.zjsu.yyd.course.model.Enrollment;
import com.zjsu.yyd.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {


    void deleteAllByCourseId(Long CourseId);




    /**  判断学生是否已选某门课 */
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    /** 获取本课程选课数量 */
    int countByCourseId(Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);
}
