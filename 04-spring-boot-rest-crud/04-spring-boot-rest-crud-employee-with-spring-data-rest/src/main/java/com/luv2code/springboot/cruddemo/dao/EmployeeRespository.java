package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "members") // i.e., if i want to change the end point from {.../employees} to {.../members}
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
    // that's it...no need to write any cod efor this...all the functions are automatically included in this
}
