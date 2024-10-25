package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    //@Before("execution(public void addAccount())")
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())") // to match on the specific class
//    @Before("execution(public void add*())")
//    @Before("execution(void add*())")
//    @Before("execution(* add*())")
//    @Before("execution(* add*(com.luv2code.aopdemo.Account))") // only woth Account class agruments
//    @Before("execution(* add*(..))") // with any number of arguments
//    @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")// with more or same number of arguments as of Account
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") // with a particular package with any class & with any method and any parameters
    public void beforeAddAccountAdvice(){
        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }
}
