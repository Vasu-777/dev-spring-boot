package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define the private field  for the dependency
    private Coach myCoach;
    @Autowired
    public void setCoach(Coach theCoach){ // In setter injection, function can be given of any name
        myCoach = theCoach;
    }
    @GetMapping("/getDailyWorkout")
    public String getDailyWorkout()
    {
        return myCoach.getDailyWorkout();
    }
}
