package com.example.onlineexamsystem.Model;

import lombok.Data;

@Data
public class QuestionRequest {
        private String questionText;
        private String correctAnswer;
        private Long examId;
}