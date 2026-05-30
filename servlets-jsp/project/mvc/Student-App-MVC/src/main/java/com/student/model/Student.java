package com.student.model;

public class Student {

    private String name;
    private String email;
    private String city;

    public Student(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getCity() {
        return city;
    }
}