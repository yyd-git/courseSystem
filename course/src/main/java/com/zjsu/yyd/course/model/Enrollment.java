package com.zjsu.yyd.course.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Enrollment {

    private String id;
    private String studentId;
    private String courseId;
    private LocalDateTime enrolledAt;

    public Enrollment() {
        this.id = UUID.randomUUID().toString();
        this.enrolledAt = LocalDateTime.now();
    }

    public Enrollment(String studentId, String courseId) {
        this();
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters & Setters
    public String getId() { return id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }
}
