package com.example.lab4;

import java.util.List;

public class Student {
    private String fullName;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String email;
    private String major;
    private List<String> courses;
    private List<String> activities;

    public Student(String fullName, String address, String city, String province,
                   String postalCode, String phone, String email,
                   String major, List<String> courses, List<String> activities) {
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.major = major;
        this.courses = courses;
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Full Name: " + fullName + "\n" +
                "Address: " + address + ", " + city + ", " + province + "\n" +
                "Postal Code: " + postalCode + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Major: " + major + "\n" +
                "Courses: " + courses + "\n" +
                "Activities: " + activities + "\n";
    }
}
