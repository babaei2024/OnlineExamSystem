package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.Course;
import com.example.onlineexamsystem.Model.CreateCourseRequest;
import com.example.onlineexamsystem.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ثبت درس
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest request) {

        if (request.getCourseName() == null || request.getCourseName().isEmpty()) {
            return ResponseEntity.badRequest().body("Course name is required");
        }
        if (request.getTeacherId() == null) {
            return ResponseEntity.badRequest().body("Teacher ID is required");
        }

        Course saved = courseService.createCourse(request.getCourseName(), request.getTeacherId());
        return ResponseEntity.ok(saved);
    }

    // دریافت لیست درس‌ها
    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }
}
