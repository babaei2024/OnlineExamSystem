package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.Course;
import com.example.onlineexamsystem.Model.Enrollment;
import com.example.onlineexamsystem.Repository.EnrollmentRepository;
import com.example.onlineexamsystem.Repository.UserRepository;
import com.example.onlineexamsystem.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enrollStudent(Long studentId, Long courseId) {

        // 1) چک تکراری بودن ثبت‌نام
        boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
        if (exists) {
            throw new RuntimeException("این دانشجو قبلاً در این دوره ثبت‌نام شده است");
        }

        // 2) ادامه منطق قبلی
        AppUser student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus("ACTIVE");

        return enrollmentRepository.save(enrollment);
    }


    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}
