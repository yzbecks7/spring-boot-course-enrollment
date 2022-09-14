package com.vibrant.demo;

import com.vibrant.demo.entity.Course;
import com.vibrant.demo.entity.Student;
import com.vibrant.demo.repository.CourseRepository;
import com.vibrant.demo.repository.StudentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			courseRepository.save(new Course(1L,"cs61a", "python", "True"));
			courseRepository.save(new Course(2L,"cs61b", "java", "True"));
			studentRepository.save(new Student(10L,"John", "male", "123@"));
			studentRepository.save(new Student(20L,"Rambo", "male", "345@"));
		};
	}

}
