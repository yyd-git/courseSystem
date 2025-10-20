package com.zjsu.yyd.course.model;

import jakarta.validation.constraints.*;

public class ScheduleSlot {
    @NotBlank
    private String dayOfWeek;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @Positive
    private int expectedAttendance;

    // getter/setter
    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public int getExpectedAttendance() { return expectedAttendance; }
    public void setExpectedAttendance(int expectedAttendance) { this.expectedAttendance = expectedAttendance; }
}
