package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    // برای کارنامه یک دوره
    List<StudentAnswer> findByStudentIdAndExamId(Long studentId , Long examId);

    // برای لیست نمرات یک امتحان برای همه دانشجوها
    List<StudentAnswer> findByExamId(Long examId);
}
