package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // کارنامه یک دوره برای یک دانش‌آموز
    @GetMapping("/course-transcript")
    public List<ReportService.ExamScoreDto> getCourseTranscript(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {
        return reportService.getStudentTranscriptForCourse(studentId, courseId);
    }

    // لیست نمرات یک امتحان برای همه دانشجوها
    @GetMapping("/exam-scores")
    public List<ReportService.ExamStudentScoreDto> getExamScores(
            @RequestParam Long examId
    ) {
        return reportService.getExamScores(examId);
    }

    // نفرات برتر یک دوره
    @GetMapping("/top-students")
    public List<ReportService.TopStudentDto> getTopStudents(
            @RequestParam Long courseId,
            @RequestParam(defaultValue = "3") int limit
    ) {
        return reportService.getTopStudentsForCourse(courseId, limit);
    }
}
