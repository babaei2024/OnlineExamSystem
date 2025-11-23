package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.Course;
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

    // ثبت درس با POST و JSON
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course courseRequest) {

        if (courseRequest.getTeacher() == null || courseRequest.getTeacher().getId() == null) {
            return ResponseEntity.badRequest().build(); // اگر ایدی استاد ارسال نشده باشد
        }

        Course saved = courseService.createCourse(
                courseRequest.getCourseName(),
                courseRequest.getTeacher().getId()
        );
        return ResponseEntity.ok(saved);
    }

    // دریافت لیست همه درس‌ها
    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }
}