package com.example.onlineexamsystem.Controller;


import com.example.onlineexamsystem.Service.ExamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exam")
@CrossOrigin(origins = "*")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

}
