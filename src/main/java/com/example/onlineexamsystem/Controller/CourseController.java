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

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(
            @RequestParam String name,
            @RequestParam Long teacherId) {
        return ResponseEntity.ok(courseService.createCourse(name, teacherId));
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }
}
