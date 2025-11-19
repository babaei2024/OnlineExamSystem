package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.Course;
import com.example.onlineexamsystem.Repository.CourseRepository;
import com.example.onlineexamsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(String courseName, Long teacherId) {
        AppUser teacher = userRepository.findById(teacherId).orElse(null);
        if (teacher == null) throw new RuntimeException("Teacher not found");

        Course course = new Course();
        course.setCourseName(courseName);
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}


