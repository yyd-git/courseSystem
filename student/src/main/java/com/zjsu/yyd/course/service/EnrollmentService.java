package com.zjsu.yyd.course.service;

import com.zjsu.yyd.course.exception.ResourceNotFoundException;
import com.zjsu.yyd.course.model.ApiResponse;
import com.zjsu.yyd.course.model.Enrollment;
import com.zjsu.yyd.course.repository.EnrollmentRepository;
import com.zjsu.yyd.course.repository.StudentRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final RestTemplate restTemplate;
    private final StudentRepository studentRepo;

    // 使用 Nacos 服务名
    private static final String COURSE_SERVICE_NAME = "catalog-service";

    public EnrollmentService(EnrollmentRepository enrollmentRepo, RestTemplate restTemplate, StudentRepository studentRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.restTemplate = restTemplate;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // 1️⃣ 检查学生是否存在
        if (!studentExists(studentId)) {
            throw new ResourceNotFoundException("学生不存在");
        }

        // 2️⃣ 检查课程是否存在
        CourseInfo course = getCourseInfo(courseId);

        // 3️⃣ 是否已选
        if (enrollmentRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("该学生已选择此课程");
        }

        // 4️⃣ 容量检查
        long enrolledCount = enrollmentRepo.countByCourseId(courseId);
        if (enrolledCount >= course.getCapacity()) {
            throw new RuntimeException("课程人数已满");
        }

        // 5️⃣ 创建记录
        Enrollment enrollment = new Enrollment(studentId, courseId);
        return enrollmentRepo.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepo.findByCourseId(courseId);
    }

    @Transactional
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepo.existsById(id)) {
            throw new RuntimeException("选课记录不存在");
        }
        enrollmentRepo.deleteById(id);
    }

    /** 调用学生repository检查学生存在 */
    private boolean studentExists(Long studentId) {
        return studentRepo.existsById(studentId);
    }

    /** 调用课程微服务获取课程信息，通过服务名 + RestTemplate */
    private CourseInfo getCourseInfo(Long courseId) {
        try {
            String url = "http://" + COURSE_SERVICE_NAME + "/api/courses/" + courseId;
            System.out.println("请求课程服务 URL: " + url);

            ResponseEntity<ApiResponse<CourseInfo>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponse<CourseInfo>>() {}
                    );

            ApiResponse<CourseInfo> apiResponse = response.getBody();
            if (apiResponse == null || apiResponse.getData() == null) {
                throw new ResourceNotFoundException("课程不存在");
            }

            return apiResponse.getData();

        } catch (Exception e) {
            e.printStackTrace();  // 打印异常，方便调试
            throw new ResourceNotFoundException("课程不存在");
        }
    }


    public int countByCourseId(Long courseId) {
        return enrollmentRepo.countByCourseId(courseId);
    }

    /** 将所有此课程的选课记录删除 */
    @Transactional
    public void deleteByCourse(Long courseId) {
        List<Enrollment> enrollments = getEnrollmentsByCourse(courseId);
        enrollmentRepo.deleteAll(enrollments);
    }

    /** 内部 DTO，用于微服务获取课程信息 */
    public static class CourseInfo {
        private Long id;
        private String title;
        private int capacity;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public int getCapacity() { return capacity; }
        public void setCapacity(int capacity) { this.capacity = capacity; }
    }
}
