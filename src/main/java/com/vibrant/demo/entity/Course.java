package com.vibrant.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private String status;


    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id") }
            )
    private Set<Student> enrolledStudents = new HashSet<>();

    public Course(Long courseId, String name, String text, String status) {
        this.courseId = courseId;
        this.name = name;
        this.text = text;
        this.status = status;
    }

    public Long getId() {
        return courseId;
    }

    public void setId(Long id) {
        this.courseId = id;
    }

    public String getCourseName() {
        return this.name;
    }

    public void setCourseName(String newName) {
        this.name = newName;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public boolean isEnrolled(Student student) {
        return enrolledStudents.contains(student);
    }
}
