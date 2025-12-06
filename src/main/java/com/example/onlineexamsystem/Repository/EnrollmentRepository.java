package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByCourseId(Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    // برای جلوگیری از ثبت‌نام تکراری
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
