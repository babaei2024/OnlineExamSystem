package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByStudentIdAndExamId(Long studentId , Long examId);
}
