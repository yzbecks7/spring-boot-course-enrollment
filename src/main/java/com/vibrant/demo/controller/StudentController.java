package com.vibrant.demo.controller;

import com.vibrant.demo.entity.Course;
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

    @GetMapping("/{id}")
    public String getCourseText(@PathVariable("id") Long courseId) {
        log.info("Inside getCourseText method of StudentController");
        return courseService.getCourseTextById(courseId);
    }
}
