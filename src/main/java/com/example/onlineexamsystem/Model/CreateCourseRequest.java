package com.example.onlineexamsystem.Model;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String courseName;
    private Long teacherId;
}

