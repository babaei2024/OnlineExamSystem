package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.Enrollment;
import com.example.onlineexamsystem.Service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // ثبت‌نام دانشجو در دوره
    @PostMapping("/create")
       public ResponseEntity<?> enrollStudent(@RequestParam Long studentId,
                                           @RequestParam Long courseId) {
        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            return ResponseEntity.ok(enrollment);
        } catch (RuntimeException e) {
            // اینجا همون پیام "این دانشجو قبلاً..." رو برمی‌گردونیم با کد 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // لیست دانشجویان یک دوره
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getByCourse(@PathVariable Long courseId) {
        return enrollmentService.getEnrollmentsByCourse(courseId);
    }

    // لیست دوره‌های یک دانشجو
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getByStudent(@PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }
}
