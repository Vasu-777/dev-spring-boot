package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    // define @PostConstruct to load the student data... only once!
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Roosi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    //  define endpoints for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }
//    @GetMapping("/students") // Not a good approach
//    public List<Student> getStudents(){
//        List<Student> theStudents = new ArrayList<>();
//        theStudents.add(new Student("Poornima", "Patel"));
//        theStudents.add(new Student("Mario", "Roosi"));
//        theStudents.add(new Student("Mary", "Smith"));
//        return theStudents;
//    }

    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // just index into the list...keeping it simple as of now

        // check the student id again list size
        if((studentId >= theStudents.size()) || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId); // Happy Path
    }
}
