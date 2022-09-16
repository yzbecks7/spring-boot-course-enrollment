package com.vibrant.demo.controller;

import com.vibrant.demo.entity.Student;
import com.vibrant.demo.service.CourseService;
import com.vibrant.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public Student saveStudent(@RequestBody Student student) {
        log.info("Inside saveStudent method of StudentController");
        return studentService.saveStudent(student);
    }

    @GetMapping("/getCourseText/{courseId}")
    public String getCourseText(@PathVariable("courseId") Long courseId) {
        log.info("Inside getCourseText method of StudentController");
        return courseService.getCourseTextById(courseId);
    }

    @PostMapping("/joinCourse/{courseId}/{studentId}")
    public String joinCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        log.info("Inside joinCourse method of StudentController");
        studentService.joinCourse(courseId, studentId);
        return "Successfully join course: " + courseId;
    }

    @PostMapping("/quitCourse/{courseId}/{studentId}")
    public String quitCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        log.info("Inside quitCourse method of StudentController");
        studentService.quitCourse(courseId, studentId);
        return "Successfully quit course: " + courseId;
    }
}
