package com.example.onlineexamsystem.Model;

import jakarta.persistence.*;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // دانشجو
    @ManyToOne
    @JoinColumn(name = "student_id")
    private AppUser student;

    // دوره (Course)
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // وضعیت ثبت‌نام
    private String status;  // مثلا: ACTIVE, COMPLETED

    public Long getId() {
        return id;
    }

    public AppUser getStudent() {
        return student;
    }

    public void setStudent(AppUser student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
