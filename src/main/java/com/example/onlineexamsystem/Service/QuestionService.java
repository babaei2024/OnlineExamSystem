package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Model.Question;
import com.example.onlineexamsystem.Repository.ExamRepository;
import com.example.onlineexamsystem.Repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;

    public QuestionService(QuestionRepository questionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }

    public Question addQuestion(Question question, Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));
        question.setExam(exam);
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByExam(Long examId) {
        return questionRepository.findByExamId(examId);
    }
}