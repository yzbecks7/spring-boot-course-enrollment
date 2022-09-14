package com.vibrant.demo.service;

import com.vibrant.demo.entity.Course;
import com.vibrant.demo.entity.Student;
import com.vibrant.demo.exception.CourseStudentException;
import com.vibrant.demo.repository.CourseRepository;
import com.vibrant.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public void saveCourse(Course course) {
        log.info("Inside saveCourse method of CourseService");
        courseRepository.save(course);
        log.info("Successfully run saveCourse");
    }

    public Course findCourseById(Long courseId) {
        log.info("Inside findCourseById method of CourseService");
        return courseRepository.findByCourseId(courseId);
    }

    public void editCourse(Course course) {
        log.info("Inside editCourse method of CourseService");
        Optional<Course> courseOptional= courseRepository.findById(course.getId());
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + course.getId());
        }
        Course newCourse = courseOptional.get();
        newCourse.setText(course.getText());
        courseRepository.save(newCourse);
        log.info("Successfully run editCourse");
    }

    public void deleteByCourseId(Long courseId) {
        log.info("Inside deleteByCourseId method of CourseService");
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        courseRepository.deleteById(courseId);
        log.info("Successfully run deleteByCourseId");
    }

    public String getCourseTextById(Long courseId) {
        log.info("Inside getCourseTextById method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        return courseOptional.get().getText();
    }

    public void addStudentToCourse(Long courseId, Student student) {
        log.info("Inside addStudentToCourse method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        //if not , throw
        Course course = courseOptional.get();
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        //if not
        Student curStudent = studentOptional.get();
        course.setStudents((Set<Student>) curStudent);
        courseRepository.save(course);
    }
}
