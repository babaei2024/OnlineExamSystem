import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseService, Course } from './services/course.service';
import { Login } from './login/login';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, Login],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent implements OnInit {
  title = 'online-exam-ui';

  courses: Course[] = [];

  private courseService = inject(CourseService);

  ngOnInit(): void {
    this.courseService.getCourses().subscribe({
      next: (data) => {
        this.courses = data;
      },
      error: (err) => {
        console.error('Error loading courses', err);
      }
    });
  }
}
