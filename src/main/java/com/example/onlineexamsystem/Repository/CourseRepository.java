package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course , Long> {

}
