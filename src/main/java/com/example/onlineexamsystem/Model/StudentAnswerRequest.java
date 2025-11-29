package com.example.onlineexamsystem.Model;

import lombok.Data;

@Data
public class StudentAnswerRequest {
    private Long questionId;
    private String studentAnswer;
    private Long studentId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
}