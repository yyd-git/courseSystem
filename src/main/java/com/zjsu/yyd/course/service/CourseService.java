package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.repository.CourseRepository;
import com.zjsu.yyd.course.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /** 获取所有课程 */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /** 根据ID获取课程 */
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    /** 创建课程 */
    @Transactional
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /** 更新课程 */
    @Transactional
    public Course updateCourse(Long id, Course updated) {
        Course existing = getCourseById(id);

        existing.setCode(updated.getCode());
        existing.setTitle(updated.getTitle());
        existing.setInstructor(updated.getInstructor());
        existing.setSchedule(updated.getSchedule());
        existing.setCapacity(updated.getCapacity());

        // 不再处理 enrollments，交给 EnrollmentService 管理
        return courseRepository.save(existing);
    }

    /** 删除课程，同时删除对应的选课记录 */
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        // 手动删除该课程对应的所有选课记录
        enrollmentRepository.deleteAllByCourseId(id);

        // 删除课程本身
        courseRepository.delete(course);
    }

    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with code: " + code));
    }

    /** 模糊搜索课程（按标题） */
    public List<Course> searchCourses(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return courseRepository.findAll();
        }
        return courseRepository.searchCoursesByTitle(keyword.trim());
    }

    /** 查询有剩余容量的课程 */
    public List<Course> getCoursesWithRemainingCapacity() {
        return courseRepository.findCoursesWithRemainingCapacity();
    }
}
