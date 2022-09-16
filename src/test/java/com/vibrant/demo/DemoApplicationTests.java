package com.vibrant.demo;

import com.vibrant.demo.entity.Course;
import com.vibrant.demo.repository.CourseRepository;
import com.vibrant.demo.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//Please delete the data files before the testing
//Also, please run the tests from the first one to the last manually.
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CourseService courseService;

	//tried @MockBean but not working.
	@Autowired
	CourseRepository courseRepository;

	@Test
	public void createCourse() {
		Course testCourse1 = new Course(1l, "CS", "spring boot", "True");
		courseRepository.save(testCourse1);
		Course testCourse2 = new Course(2l, "CS2", "Java", "True");
		courseRepository.save(testCourse2);
		assertEquals(2, courseService.getCourses().size());
	}

	@Test
	public void deleteCourse() {
		courseService.deleteByCourseId(1L);
		assertEquals(1, courseService.getCourses().size());
	}

	@Test
	public void checkCourseStatusById() {
		assertEquals("True", courseService.checkCourseStatusById(2L));
	}

}
