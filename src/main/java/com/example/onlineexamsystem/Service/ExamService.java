package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.Course;
import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Repository.CourseRepository;
import com.example.onlineexamsystem.Repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public ExamService(ExamRepository examRepository , CourseRepository courseRepository){
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }
    public Exam createExam(String examTitle, String examDate, String courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) throw new RuntimeException("Course not found");

        Exam exam = new Exam();
        exam.setExamTitle(examTitle);
        exam.setExamDate(examDate);
        exam.setCourse(course);

        return examRepository.save(exam);
    }
    public List<Exam> getExams() {
        return examRepository.findAll();
    }
}
