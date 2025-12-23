import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';

export interface Course {
  id: number;
  courseName: string;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8081/api/courses';

  getCourses() {
    return this.http.get<Course[]>(this.baseUrl);
  }
}
