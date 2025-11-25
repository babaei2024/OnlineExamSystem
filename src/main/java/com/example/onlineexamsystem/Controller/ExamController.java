package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody Map<String, Object> request) {

        String examTitle = (String) request.get("examTitle");
        String examDate = (String) request.get("examDate");
        Long courseId = Long.valueOf(request.get("courseId").toString());

        Exam saved = examService.createExam(examTitle, examDate, courseId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Exam> getExams() {
        return examService.getExams();
    }
}