package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.Question;
import com.example.onlineexamsystem.Model.StudentAnswer;
import com.example.onlineexamsystem.Model.StudentAnswerRequest;
import com.example.onlineexamsystem.Repository.QuestionRepository;
import com.example.onlineexamsystem.Repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentAnswerService {

    private final QuestionRepository questionRepository;
    private final StudentAnswerRepository studentAnswerRepository;

    public StudentAnswerService(QuestionRepository questionRepository,
                                StudentAnswerRepository studentAnswerRepository) {
        this.questionRepository = questionRepository;
        this.studentAnswerRepository = studentAnswerRepository;
    }

    public String checkAnswer(StudentAnswerRequest request) {
        Question question = questionRepository.findById(request.getQuestionId()).orElse(null);
        if (question == null) return "Question not found";

        boolean isCorrect = question.getCorrectAnswer().equals(request.getStudentAnswer());

        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setQuestionId(request.getQuestionId());
        studentAnswer.setStudentAnswer(request.getStudentAnswer());
        studentAnswer.setIsCorrect(isCorrect);
        studentAnswerRepository.save(studentAnswer);

        return isCorrect ? "Correct answer" : "Wrong answer";
    }
}