package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runnner -> {
//			createCourseAndStudents(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);
//			addMoreCoursesForStudent(appDAO);
//			deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting Student id: " + theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create more courses
		Course tempCourse1 = new Course("Rubic Cube");
		Course tempCourse2 = new Course("Atari 2600");

		//add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: " + tempStudent);
		System.out.println("associated courses: " + tempStudent.getCourses());
		appDAO.update(tempStudent);
		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses " + tempStudent.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theID = 16;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theID);
		System.out.println("Loaded Course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create the course
		Course tempCourse = new Course("Pacman1 - Mein hoon Ninha Hatori");

		// create the students
		Student tempStudent1 = new Student("Vasu", "Nagori", "vasunagori777@gmaail.com");
		Student tempStudent2 = new Student("Ayush", "Bansal", "ayushbansal111@gmail.com");
		Student tempStudent3 = new Student("Prakhar", "Kansal", "prakharkansal41@gmail.com");

		// add the students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		tempCourse.addStudent(tempStudent3);

		// save the course and the associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("Associated Students:" + tempCourse.getStudents());

		appDAO.save(tempCourse);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting the course id:" + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReview(AppDAO appDAO) {
		// create the course
		Course tempCourse = new Course("Pacman - How to score One Million Points");

		// add some reviews
//		Review tempreview = new Review("Great Course...Loved it!!");
//		tempCourse.addReview(tempreview);      OR
		tempCourse.addReview(new Review("Great Course...Loved it!!"));
		tempCourse.addReview(new Review("Cool course, job well done."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 18;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy The Simple Things");

		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating the instructor: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		// *** REMEMBER: in join fetch SPAce will be there before double quotes in AppDAOImpl under this function ***

		// find the Instructor
		System.out.println("Finding Instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		//find courses for instructor
		System.out.println("Finding courses for Instructor id: " + theId);
		List<Course> courses = appDAO.findCourseByInstructorId(theId);
		// associate the objects
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInsturctor = new Instructor("Vasu", "Nagori", "vasunagori777@gmail.com");
		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luvecode.com/youtube", "become a great Businessman");
		// associate the objects
		tempInsturctor.setInstructorDetail(tempInstructorDetail);
		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");
		// add courses to instructor
		tempInsturctor.add(tempCourse1);
		tempInsturctor.add(tempCourse2);

		// save the instructor
		//
		// NOTE: This will also save the courses
		// beacuse of CascadeType.PERSIST
		//
		System.out.println("Saving Instructor: " + tempInsturctor);
		System.out.println("The courses: " + tempInsturctor.getCourses());
		appDAO.save(tempInsturctor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 6;
		System.out.println("Deleting instructor detail id: " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
		int theId = 1;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done meri jaan!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
//		System.out.println("the associated instructor detail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		// create the instructor
		Instructor tempInsturctor = new Instructor("Chad", "Darby", "darby@luv2code.com");
		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luvecode.com/youtube", "Luv 2 code!!");
		*/
		// create the instructor
		Instructor tempInsturctor = new Instructor("Vasu", "Nagori", "vasunagori777@gmail.com");
		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luvecode.com/youtube", "become a great Businessman");
		// associate the objects
		tempInsturctor.setInstructorDetail(tempInstructorDetail);
		// save the instructor
		//
		// NOTE: this will also save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving the Instructor: " + tempInsturctor);
		appDAO.save(tempInsturctor);

		System.out.println("Done!!");
	}

}
