package com.vibrant.demo.controller;

import com.vibrant.demo.entity.Course;
import com.vibrant.demo.entity.Student;
import com.vibrant.demo.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public String saveCourse(@RequestBody Course course) {
        log.info("Inside saveCourse method of CourseController");
        courseService.saveCourse(course);
        return course.getName() + " course has been added...";
    }

    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable("id") Long courseId) {
        log.info("Inside findCourseById method of CourseController");
        return courseService.findCourseById(courseId);
    }

    @PostMapping("/edit/{id}")
    public String editCourseById(@RequestBody Course course) {
        log.info("Inside editCourseById method of CourseController");
        courseService.editCourse(course);
        return "The description of course: " + course.getName() + " has been updated.";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourseById(@PathVariable("id") Long courseId) {
        log.info("Inside deleteCourseById method of CourseController");
        courseService.deleteByCourseId(courseId);
        return "The course with id: " + courseId + " has been deleted.";
    }

    @PostMapping("/addStudents/{id}")
    public String addStudentToCourse(@PathVariable Long courseId, @RequestBody Student student) {
        log.info("Inside addStudentToCourse method of CourseController");
        courseService.addStudentToCourse(courseId, student);
        return "Student has been successfully enrolled";
    }

}
