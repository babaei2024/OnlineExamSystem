package com.example.onlineexamsystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser student;

    @ManyToOne
    private Question question;
    private Long questionId;
    private String studentAnswer;
    private Boolean isCorrect;

    @ManyToOne
    private Exam exam;

}
