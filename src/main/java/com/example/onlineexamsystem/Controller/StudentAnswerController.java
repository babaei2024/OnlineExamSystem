package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.StudentAnswer;
import com.example.onlineexamsystem.Model.StudentAnswerRequest;
import com.example.onlineexamsystem.Service.StudentAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-answers")
@CrossOrigin(origins = "*")
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    public StudentAnswerController(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentAnswer> createAnswer(@RequestBody StudentAnswerRequest request) {

        StudentAnswer saved = studentAnswerService.saveAnswer(
                request.getStudentId(),
                request.getQuestionId(),
                request.getStudentAnswer(),
                request.getScore(),
                request.isCorrect()
        );

        return ResponseEntity.ok(saved);
    }
}
