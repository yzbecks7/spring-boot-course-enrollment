package com.vibrant.demo.exception;

public class CourseStudentException extends IllegalStateException{
    private static final long serialVersionUID = 1L;

    public CourseStudentException(String message) {
        super(message);
    }

    public CourseStudentException(Throwable e) {
        super(e);
    }
}
