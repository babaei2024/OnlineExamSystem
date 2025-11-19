package com.example.onlineexamsystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String courseName;

    @ManyToOne
    private AppUser teacher;  // هر درس متعلق به یک استاد است
}
