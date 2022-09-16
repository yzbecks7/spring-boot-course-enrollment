package com.vibrant.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;


    public Student(long studentId, String name, String gender, String email) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.email = email;
    }

    public Long getId() {
        return studentId;
    }

    public void setId(Long id) {
        this.studentId = id;
    }

    public String getStudentName() {
        return this.name;
    }

    public void setStudentName(String newName) {
        this.name = newName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String newGender) {
        this.gender = newGender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
}
