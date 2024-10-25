package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findMyId(Integer Id);
    List<Student> findAll();
    List<Student> findByLastname(String thelastName);
    void update(Student theStudent);
    void delete(Integer Id);
    int deleteAll();
}
