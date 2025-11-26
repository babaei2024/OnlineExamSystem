package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Model.Question;
import com.example.onlineexamsystem.Model.QuestionRequest;
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

    // ایجاد سوال
    public Question createQuestion(QuestionRequest request) {
        Exam exam = examRepository.findById(request.getExamId()).orElse(null);
        if (exam == null) throw new RuntimeException("Exam not found");

        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setExam(exam);

        return questionRepository.save(question);
    }

    // دریافت سوالات هر آزمون
    public List<Question> getQuestionsByExamId(Long examId) {
        return questionRepository.findByExamId(examId);
    }
}