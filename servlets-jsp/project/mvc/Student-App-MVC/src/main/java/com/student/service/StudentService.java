package com.student.service;

import com.student.model.Student;

public class StudentService {

    public Student registerStudent(String name, String email, String city) {
        // Validation can be added here e.g. checking empty fields, email format, etc.
        return new Student(name, email, city);
    }
}