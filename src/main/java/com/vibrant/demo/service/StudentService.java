package com.vibrant.demo.service;

import com.vibrant.demo.entity.Student;
import com.vibrant.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        log.info("Inside saveStudent method of StudentService");
        return studentRepository.save(student);
    }
}
