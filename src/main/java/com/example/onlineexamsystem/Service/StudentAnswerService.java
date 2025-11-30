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
        if (question == null) {
            return "Question not found";
        }

        boolean isCorrect = question.getCorrectAnswer().equals(request.getStudentAnswer());

        // ذخیره پاسخ دانشجو در دیتابیس
        StudentAnswer answer = new StudentAnswer();
        answer.setStudentId(request.getStudentId());
        answer.setQuestion(question);
        answer.setExam(question.getExam());
        answer.setStudentAnswer(request.getStudentAnswer());
        answer.setCorrect(isCorrect);
        answer.setScore(isCorrect ? 1 : 0);
        studentAnswerRepository.save(answer);

        return isCorrect ? "Correct answer - Score: 1" : "Wrong answer - Score: 0";
    }
    }