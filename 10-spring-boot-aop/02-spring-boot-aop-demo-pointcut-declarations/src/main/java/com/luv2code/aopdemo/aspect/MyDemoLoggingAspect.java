package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        //print out method we are advicing on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @Around on method" + method);

        //get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed(); // theProceedingJoinPoint will handle to target method; proceed() will execute the target method
        }
        catch (Exception exc){
            // log the exception
            System.out.println(exc.getMessage());

            // rethrow exception
            throw exc;
            /*
            // give user a custom message
            result = "Major accident! But no worries, your private AOP helicopter is on the way";
             */
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n===>>>> Duration: " + duration / 1000.0 + " seconds"); // /1000.0 to convert milliseconds to seconds
        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void AfterFinallyFoundAccountsAdvice(JoinPoint theJoinPoint){
        // print out which method we are advicing on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @After{finally} on method" + method);
    }
    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
                    throwing = "theExc")
    public  void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){

        // print out which method we are advicing on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @AfterThrowing on method" + method);

        // log the exception
        System.out.println("\n====>>>> The exception is: " + theExc);
    }

//     add a newadvice for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
                    returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result){

        // print out on which method we are advicing on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @AfterReturning on method" + method);

        // print out the results of the method call
        System.out.println("\n====>>>> result is" + result);

        // let's post-process the data...let's modify it :)

        // convert the account names to upper case
        convertAccountNamesToUpperCase(result);
        System.out.println("\n====>>>> result is" + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through account
        for (Account tempAccount : result) {
            // get uppercase version of names
            String theUpperCase = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperCase);
        }
    }


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

//    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
//    private void forDaoPackage(){}


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint){
        System.out.println("\n====>>> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments

        //get args
        Object[] args = theJointPoint.getArgs();

        // loop through args
        for(Object tempArg : args){
            System.out.println(tempArg);
            if(tempArg instanceof Account){
                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;
                System.out.println("Account Name: " + theAccount.getName());
                System.out.println("Account Level: " + theAccount.getLevel());
            }
        }

    }



//    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") // with a particular package with any class & with any method and any parameters
//    public void beforeAddAccountAdvice(){
//        System.out.println("\n====>>> Executing @Before advice on addAccount()");
//    }



////  create a pointcut for getter methods
//    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
//    private void getter(){}
//
//    // create a pointcut for setter methods
//    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
//    private void setter(){}
//
//    // create a pointcut: include package ... exclude getter/setter
//    @Pointcut("forDaoPackage() && !(getter() || setter())")    // ***   IN THIS THE EXECUTION WORD WILL NOT BE THERE IN THE POINTCUT ***
//    private void forDaoPackageNoGetterSetter(){}

//    @Before("forDaoPackageNoGetterSetter()")
//    public void performApiAnalytics(){
//        System.out.println("\n===>>> Performing Api Analytics");
//    }

//    @Before("forDaoPackageNoGetterSetter()")
//    public void logToCloudAsync(){
//        System.out.println("\n===>>> Logging to cloud in async fashion");
//    }
}

