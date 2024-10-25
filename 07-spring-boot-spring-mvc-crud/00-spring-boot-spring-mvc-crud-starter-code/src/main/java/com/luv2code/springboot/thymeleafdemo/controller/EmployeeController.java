package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
@Autowired // Since there is one constructer so Autowired annotation is optional
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // add mapping for the "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){
         // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();
        // add the spring model
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees"; // i.e., will now create Thymeleaf Page
    }

    @GetMapping("/showFormForAdd")
    public String ShowFormForAdd(Model theModel){
    Employee theEmployee = new Employee();
    theModel.addAttribute("employee", theEmployee);
    return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@ModelAttribute("employeeId") int theId, Model theModel){

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee in  the model to prepopulate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        // save the employee
        employeeService.save(theEmployee);
        // use a redirect to prevent duplicate submission
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){

        // delete the employee
        employeeService.deleteById(theId);
        // redirect to /employees/list
        return "redirect:/employees/list";
    }
}
