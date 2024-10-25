package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    // quick amd dirty: inject employee dao (using constructor injection)


    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return the list of employees
    @GetMapping("/employees")
    public List<Employee> findALl(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        // System.out.println("check" + theEmployee); i.e., contains all the given details pf employee
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found: " + employeeId);
        }
        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also just in case they pass an id in the JSON...set id to 0
        // this is to save a force of new item...instead of update
        theEmployee.setId(0);
        Employee dbEmployees = employeeService.save(theEmployee);
        return dbEmployees;
    }

    // add mapping for PUT /employuee - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee tempEmployee = employeeService.findById(employeeId);
        // throw exception if null
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found: " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Employee Deleted Successfully: " + employeeId;
    }
}
