package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.Enrollment;
import com.example.onlineexamsystem.Model.Exam;
import com.example.onlineexamsystem.Model.StudentAnswer;
import com.example.onlineexamsystem.Repository.EnrollmentRepository;
import com.example.onlineexamsystem.Repository.ExamRepository;
import com.example.onlineexamsystem.Repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private final ExamRepository examRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ReportService(ExamRepository examRepository,
                         StudentAnswerRepository studentAnswerRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.examRepository = examRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // کارنامه یک دوره برای یک دانش‌آموز
    public static class ExamScoreDto {
        public Long examId;
        public String examTitle;
        public int totalScore;
        public int questionCount;
    }

    public List<ExamScoreDto> getStudentTranscriptForCourse(Long studentId, Long courseId) {

        List<Exam> exams = examRepository.findByCourseId(courseId);
        List<ExamScoreDto> result = new ArrayList<>();

        for (Exam exam : exams) {
            List<StudentAnswer> answers =
                    studentAnswerRepository.findByStudentIdAndExamId(studentId, exam.getId());

            int totalScore = answers.stream()
                    .mapToInt(StudentAnswer::getScore)
                    .sum();

            ExamScoreDto dto = new ExamScoreDto();
            dto.examId = exam.getId();
            dto.examTitle = exam.getExamTitle();
            dto.totalScore = totalScore;
            dto.questionCount = answers.size();

            result.add(dto);
        }

        return result;
    }

    // لیست نمرات یک امتحان برای همه دانشجوها
    public static class ExamStudentScoreDto {
        public Long studentId;
        public String studentName; // فعلاً ساده
        public int totalScore;
    }

    public List<ExamStudentScoreDto> getExamScores(Long examId) {

        List<StudentAnswer> answers = studentAnswerRepository.findByExamId(examId);

        Map<Long, ExamStudentScoreDto> map = new HashMap<>();

        for (StudentAnswer sa : answers) {
            Long studentId = sa.getStudentId();

            ExamStudentScoreDto dto = map.get(studentId);
            if (dto == null) {
                dto = new ExamStudentScoreDto();
                dto.studentId = studentId;
                dto.studentName = "Student " + studentId; // بعداً می‌توانیم از AppUser پر کنیم
                dto.totalScore = 0;
                map.put(studentId, dto);
            }

            dto.totalScore += sa.getScore();
        }

        return new ArrayList<>(map.values());
    }

    // نفرات برتر یک دوره (Top N دانشجو بر اساس مجموع نمره در همه امتحان‌های دوره)
    public static class TopStudentDto {
        public Long studentId;
        public int totalScore;
    }

    public List<TopStudentDto> getTopStudentsForCourse(Long courseId, int limit) {

        // 1) همه‌ی ثبت‌نام‌های این دوره
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        List<TopStudentDto> result = new ArrayList<>();

        // 2) برای هر دانشجو، مجموع نمره‌اش در این دوره را حساب کن
        for (Enrollment enrollment : enrollments) {

            Long studentId = enrollment.getStudent().getId();

            List<ExamScoreDto> transcript = getStudentTranscriptForCourse(studentId, courseId);

            int total = transcript.stream()
                    .mapToInt(e -> e.totalScore)
                    .sum();

            TopStudentDto dto = new TopStudentDto();
            dto.studentId = studentId;
            dto.totalScore = total;

            result.add(dto);
        }

        // 3) مرتب‌سازی نزولی بر اساس totalScore
        result.sort((a, b) -> Integer.compare(b.totalScore, a.totalScore));

        // 4) محدود کردن به Top N
        if (result.size() > limit) {
            return result.subList(0, limit);
        }
        return result;
    }
}
