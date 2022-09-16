package com.vibrant.demo.controller;

import com.vibrant.demo.entity.Course;
import com.vibrant.demo.entity.Student;
import com.vibrant.demo.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public Course saveCourse(@RequestBody Course course) {
        log.info("Inside saveCourse method of CourseController");
        return courseService.saveCourse(course);
    }

    @GetMapping("/{courseId}")
    public String checkCourseStatusById(@PathVariable("courseId") Long courseId) {
        log.info("Inside checkCourseStatusById method of CourseController");
        return courseService.checkCourseStatusById(courseId);
    }

    @PostMapping("/edit/{courseId}")
    public String editCourseById(@RequestBody Course course) {
        log.info("Inside editCourseById method of CourseController");
        courseService.editCourse(course);
        return "The description of course: " + course.getId() + " has been updated.";
    }

    @PostMapping("/delete/{courseId}")
    public String deleteCourseById(@PathVariable("courseId") Long courseId) {
        log.info("Inside deleteCourseById method of CourseController");
        courseService.deleteByCourseId(courseId);
        return "The course with id: " + courseId + " has been deleted.";
    }

    @PostMapping("/addStudent/{courseId}/{studentId}")
    public String addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        log.info("Inside addStudentToCourse method of CourseController");
        courseService.addStudentToCourse(courseId, studentId);
        return "Student has been successfully enrolled";
    }

    @PostMapping("/removeStudent/{courseId}/{studentId}")
    public String removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        log.info("Inside removeStudentFromCourse method of CourseController");
        courseService.removeStudentFromCourse(courseId, studentId);
        return "Student has been successfully removed.";
    }

    @GetMapping("/checkNumberOfStudent/{courseId}")
    public String checkNumberOfStudent(@PathVariable Long courseId) {
        log.info("Inside checkNumberOfStudent method of CourseController");
        return courseService.checkNumberOfStudent(courseId);
    }

    @GetMapping("/checkStudentsInfo/{courseId}")
    public List<Student> checkStudentInfoByCourseId(@PathVariable Long courseId) {
        log.info("Inside checkStudentInfoByCourseId method of CourseController");
        return courseService.checkStudentInfoByCourseId(courseId);
    }

}
