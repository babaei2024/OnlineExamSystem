package com.example.onlineexamsystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examTitle;
    private String examDate;

    @ManyToOne
    private Course course;
}



