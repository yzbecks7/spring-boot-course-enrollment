package com.vibrant.demo.service;

import com.vibrant.demo.entity.Student;
import com.vibrant.demo.repository.CourseRepository;
import com.vibrant.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    public Student saveStudent(Student student) {
        log.info("Inside saveStudent method of StudentService");
        return studentRepository.save(student);
    }


    public void joinCourse(Long courseId, Long studentId) {
        log.info("Inside joinCourse method of StudentService");
        courseService.addStudentToCourse(courseId,studentId);
        log.info("Successfully run joinCourse");
    }

    public void quitCourse(Long courseId, Long studentId) {
        log.info("Inside quitCourse method of StudentService");
        courseService.removeStudentFromCourse(courseId, studentId);
        log.info("Successfully run quitCourse");
    }
}
