package com.zjsu.yyd.course.model;

import jakarta.validation.constraints.*;

public class Instructor {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @Email
    private String email;

    // getter/setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
