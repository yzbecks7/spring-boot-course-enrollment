package com.vibrant.demo.service;

import com.vibrant.demo.entity.Course;
import com.vibrant.demo.entity.Student;
import com.vibrant.demo.exception.CourseStudentException;
import com.vibrant.demo.repository.CourseRepository;
import com.vibrant.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Course saveCourse(Course course) {
        log.info("Inside saveCourse method of CourseService");
        List<Course> courses = courseRepository.findAll();
        String courseName = course.getName();
        for (Course oldCourse: courses) {
            if (oldCourse.getName().equals(courseName)) {
                throw new CourseStudentException("Course: " + courseName + " already created.");
            }
        }
        return courseRepository.save(course);
    }

    public String checkCourseStatusById(Long courseId) {
        log.info("Inside checkCourseStatusById method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        return courseOptional.get().getStatus();
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
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
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

    public void addStudentToCourse(Long courseId, Long studentId) {
        log.info("Inside addStudentToCourse method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        Course course = courseOptional.get();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new CourseStudentException("Invalid studentId: " + studentId);
        }
        Student student = studentOptional.get();

        //check if students already enrolled
        if (course.isEnrolled(student)) {
            throw new CourseStudentException("Student: " + studentId + " already enrolled.");
        }
        course.enrollStudent(student);
        courseRepository.save(course);
        log.info("Successfully run addStudentToCourse");
    }

    public void removeStudentFromCourse(Long courseId, Long studentId) {
        log.info("Inside removeStudentFromCourse method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        Course course = courseOptional.get();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new CourseStudentException("Invalid studentId: " + studentId);
        }
        Student student = studentOptional.get();

        //check if student has been enrolled
        if (!course.isEnrolled(student)) {
            throw new CourseStudentException("Student: " + studentId + " not enrolled");
        }
        course.removeStudent(student);
        courseRepository.save(course);
        log.info("Successfully run removeStudentFromCourse");
    }

    public String checkNumberOfStudent(Long courseId) {
        log.info("Inside checkNumberOfStudent method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        Course course = courseOptional.get();
        Set<Student> students = course.getEnrolledStudents();
        return String.valueOf(students.size());
    }

    public List<Student> checkStudentInfoByCourseId(Long courseId) {
        log.info("Inside checkStudentInfoByCourseId method of CourseService");
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new CourseStudentException("Invalid courseId: " + courseId);
        }
        Course course = courseOptional.get();
        List<Student> students = new ArrayList<>();
        students.addAll(course.getEnrolledStudents());
        return students;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
