package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository // sub class @Component Annotation
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addSillyAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A Membership ACCOUNT");
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": I'm going to sleep now...");
    }
}
