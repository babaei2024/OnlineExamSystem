package com.example.onlineexamsystem.Model;

import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Model.Question;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private String studentAnswer;

    private boolean correct;

    private int score;
}