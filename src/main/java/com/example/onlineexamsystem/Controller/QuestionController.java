package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.Question;
import com.example.onlineexamsystem.Service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@RequestBody Map<String, Object> request) {

        Long examId = Long.valueOf(request.get("examId").toString());

        Question question = new Question();
        question.setTitle(request.get("title").toString());
        question.setOption1(request.get("option1").toString());
        question.setOption2(request.get("option2").toString());
        question.setOption3(request.get("option3").toString());
        question.setOption4(request.get("option4").toString());
        question.setCorrectAnswer(request.get("correctAnswer").toString());

        return ResponseEntity.ok(questionService.addQuestion(question, examId));
    }

    @GetMapping("/{examId}")
    public List<Question> getQuestions(@PathVariable Long examId) {
        return questionService.getQuestionsByExam(examId);
    }
}