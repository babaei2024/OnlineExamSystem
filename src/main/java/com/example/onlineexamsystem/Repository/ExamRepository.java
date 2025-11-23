package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ExamRepository implements JpaRepository<Exam, Long> {
}
