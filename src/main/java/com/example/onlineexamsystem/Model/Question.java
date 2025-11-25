package com.example.onlineexamsystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String correctAnswer;

    @ManyToOne
    private Exam exam;
}