package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Model.Question;
import com.example.onlineexamsystem.Model.StudentAnswer;
import com.example.onlineexamsystem.Model.StudentAnswerRequest;
import com.example.onlineexamsystem.Repository.ExamRepository;
import com.example.onlineexamsystem.Repository.QuestionRepository;
import com.example.onlineexamsystem.Repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;

    public StudentAnswerService(StudentAnswerRepository studentAnswerRepository,
                                QuestionRepository questionRepository,
                                ExamRepository examRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }

    public String checkAnswer(StudentAnswerRequest request) {

        // 1) پیدا کردن سوال
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // 2) مقایسه جواب دانشجو با جواب درست سوال
        boolean correct = question.getCorrectAnswer().equals(request.getStudentAnswer());

        // 3) تعیین نمره ساده (فعلاً اگر درست بود 1، اگر غلط بود 0)
        int score = correct ? 1 : 0;

        // 4) ذخیره‌ی جواب در جدول StudentAnswer
        saveAnswer(
                request.getStudentId(),         // studentId
                request.getQuestionId(),        // questionId
                request.getStudentAnswer(),     // studentAnswerText
                score,
                correct
        );

        // 5) برگرداندن پیام متنی برای فرانت
        return correct ? "Correct answer" : "Wrong answer";
    }

    public StudentAnswer saveAnswer(Long studentId,
                                    Long questionId,
                                    String studentAnswerText,
                                    int score,
                                    boolean correct) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Exam exam = question.getExam(); // چون هر Question به یک Exam وصل است

        StudentAnswer sa = new StudentAnswer();
        sa.setStudentId(studentId);
        sa.setQuestion(question);
        sa.setExam(exam);
        sa.setStudentAnswer(studentAnswerText);
        sa.setScore(score);
        sa.setCorrect(correct);

        return studentAnswerRepository.save(sa);
    }

    // همان متدهایی که برای Report استفاده می‌کنی را نگه‌دار، مثل:
    public List<StudentAnswer> findByStudentIdAndExamId(Long studentId, Long examId) {
        return studentAnswerRepository.findByStudentIdAndExamId(studentId, examId);
    }
}
