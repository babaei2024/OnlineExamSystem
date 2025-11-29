package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.Question;
import com.example.onlineexamsystem.Model.QuestionRequest;
import com.example.onlineexamsystem.Model.StudentAnswer;
import com.example.onlineexamsystem.Model.StudentAnswerRequest;
import com.example.onlineexamsystem.Service.QuestionService;
import com.example.onlineexamsystem.Service.StudentAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;
    private final StudentAnswerService studentAnswerService; // نام صحیح و یکنواخت

    // فقط یک کانستراکتور که تمام وابستگی‌ها را می‌پذیرد
    public QuestionController(QuestionService questionService, StudentAnswerService studentAnswerService) {
        this.questionService = questionService;
        this.studentAnswerService = studentAnswerService;
    }

    // افزودن سوال
    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@RequestBody QuestionRequest request) {
        if (request.getExamId() == null) {
            return ResponseEntity.badRequest().body("examId is required");
        }
        Question saved = questionService.createQuestion(request);
        return ResponseEntity.ok(saved);
    }

    // گرفتن سوالات هر آزمون
    @GetMapping("/exam/{examId}")
    public List<Question> getQuestions(@PathVariable Long examId) {
        return questionService.getQuestionsByExamId(examId);
    }

    // ثبت پاسخ دانشجو
    @PostMapping("/answer")
    public ResponseEntity<String> answerQuestion(@RequestBody StudentAnswerRequest request) {
        String result = studentAnswerService.checkAnswer(request);
        return ResponseEntity.ok(result);
    }
}