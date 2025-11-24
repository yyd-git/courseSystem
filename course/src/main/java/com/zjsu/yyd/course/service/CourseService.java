package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.Course;
import com.zjsu.yyd.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final RestTemplate restTemplate;

    // 通过服务名调用 enrollment-service
    private static final String ENROLLMENT_SERVICE_NAME = "enrollment-service";

    public CourseService(CourseRepository courseRepository, RestTemplate restTemplate) {
        this.courseRepository = courseRepository;
        this.restTemplate = restTemplate;
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

    /** 按课程代码查询 */
    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with code: " + code));
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

        return courseRepository.save(existing);
    }

    /** 删除课程（通过 REST 调用 enrollment-service 清理该课程选课记录） */
    @Transactional
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);

        // 调用选课微服务删除本课程的所有选课记录
        String url = "http://" + ENROLLMENT_SERVICE_NAME + "/api/enrollments/course/" + id;
        restTemplate.delete(url);

        // 删除课程
        courseRepository.delete(course);
    }

    /** 模糊搜索课程 */
    public List<Course> searchCourses(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return courseRepository.findAll();
        }
        return courseRepository.searchCoursesByTitle(keyword.trim());
    }

    /** 查询当前容量还有空位的课程（调用选课微服务获取报名人数） */
    public List<Course> getCoursesWithRemainingCapacity() {
        List<Course> all = courseRepository.findAll();

        return all.stream()
                .filter(c -> {
                    String url = "http://" + ENROLLMENT_SERVICE_NAME + "/api/enrollments/course/" + c.getId() + "/count";

                    Map<String, Object> response = restTemplate.getForObject(url, Map.class);
                    Integer count = 0;
                    if (response != null && response.get("data") != null) {
                        count = ((Number) response.get("data")).intValue();
                    }

                    return count < c.getCapacity();
                })
                .toList();
    }
}
