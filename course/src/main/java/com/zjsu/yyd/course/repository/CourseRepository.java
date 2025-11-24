package com.zjsu.yyd.course.repository;

import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /** 根据课程编号查找课程 */
    Optional<Course> findByCode(String code);

    /** 判断课程编号是否存在 */
    boolean existsByCode(String code);

    /** 根据教师查询所授课程 */
    List<Course> findByInstructor(Instructor instructor);

    /** 删除某个教师的所有课程（常用于教师删除时的级联操作） */
    void deleteAllByInstructor(Instructor instructor);

    /** 按标题关键字模糊搜索课程 */
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword%")
    List<Course> searchCoursesByTitle(@Param("keyword") String keyword);


}
