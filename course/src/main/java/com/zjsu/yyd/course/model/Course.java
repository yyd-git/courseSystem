package com.zjsu.yyd.course.model;

import jakarta.validation.constraints.*;
import java.util.*;

public class Course {
    private String id;

    @NotBlank(message = "课程编号不能为空")
    private String code;

    @NotBlank(message = "课程标题不能为空")
    private String title;

    @NotNull(message = "授课教师信息不能为空")
    private Instructor instructor;

    @NotNull(message = "课程时间安排不能为空")
    private ScheduleSlot schedule;

    @Positive(message = "容量必须为正数")
    private int capacity;

    // 当前已选人数
    private int enrolled = 0;

    public Course() {
        this.id = UUID.randomUUID().toString();
    }

    // getter / setter
    public String getId() { return id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public ScheduleSlot getSchedule() { return schedule; }
    public void setSchedule(ScheduleSlot schedule) { this.schedule = schedule; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getEnrolled() { return enrolled; }
    public void setEnrolled(int enrolled) { this.enrolled = enrolled; }
}
