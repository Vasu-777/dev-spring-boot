package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runnner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
			deleteInstructor(appDAO);
		};
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
