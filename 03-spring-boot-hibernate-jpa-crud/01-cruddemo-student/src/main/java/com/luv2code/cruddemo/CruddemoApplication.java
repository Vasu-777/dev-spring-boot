package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner CommandLineRunner(String[] args){
//		return runner -> { //Lamda basically a shortcut notation for providing an implementation of the command line runner interface.
//			System.out.println("Hello World");
//		};
//	}
//    @Bean
//    public CommandLineRunner CommandLineRunner(StudentDAO studentDAO){
//     return runner -> {
////       createStudent(studentDAO);
//         // Now writing function for entering multiple entries of students in database
//         createMultipleStudents(studentDAO);
//     };
//    }
//    @Bean
//    public CommandLineRunner CommandLineRunner(StudentDAO studentDAO){
//        return runner -> {
////       createStudent(studentDAO);
//            // Now writing function for entering multiple entries of students in database
////            createMultipleStudents(studentDAO);
//            readStudent(studentDAO);
//        };
//    }

    @Bean
    public CommandLineRunner CommandLineRunner(StudentDAO studentDAO){
        return runner -> {
//       createStudent(studentDAO);
            // Now writing function for entering multiple entries of students in database
            createMultipleStudents(studentDAO);
//            readStudent(studentDAO);
//              queryForStudents(studentDAO);
//              queryForStudentsByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
//            deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println(numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId =2; //Works Integer studentId = 2;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        // retrieve students based on the id: primary key
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studentDAO.findMyId(studentId);

        // change first name to "Scooby"
        System.out.println("Updating Student...");
        myStudent.setFirst_name("Scooby");

        // update the student
        studentDAO.update(myStudent);

        // display the updated student
        System.out.println("Updated Student:" + myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        // get the list of students
        List<Student> theStudents = studentDAO.findByLastname("Deo");

        // display the list pf students
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        // get the list of students
        List<Student> theStudents = studentDAO.findAll();
        //display the list of students
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent); // For return there should be a variable that has something and it's there in function
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        // Create the student object
        System.out.println("Creating a new student object");
        Student tempStudent = new Student("John", "Deo", "john@luv2code.com");

        // Save the student
        System.out.println("Saving the student");
        studentDAO.save(tempStudent);

        // Display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student ki Generated id:" + theId);

        // Retrieve student based on the id: Primary Key
        System.out.println("Retrieving student with id:" + theId);
        Student myStudent = studentDAO.findMyId(theId);

        // Display Student
        System.out.println("Found the student" + myStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        // Now this is the function for entering entries of multiple students in the database
        // create the multiple students
        System.out.println("Creating the students...");
        Student tempStudent1 = new Student("Paul", "Joe", "paul@luv2code.com");
        Student tempStudent2 = new Student("Bonita", "Applebomb", "bonita@luv2code.com");
        Student tempStudent3 = new Student("Vasu", "Nagori", "vasu@luv2code.com");

        // save the students objects
        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    /* This is the main coding for crating the student and saving them in the database. */
    private void createStudent(StudentDAO studentDAO) {
        // create the student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

        // save the student object
        System.out.println("Saving the student..,");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Save student, Generate id:" + tempStudent.getId());
    }
}
