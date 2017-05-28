package com.amaysim.coding.domain.entities;

public class User {

    public final String title;
    public final String firstName;
    public final String lastName;
    public final String dateOfBirth;
    public final String contactNumber;
    public final String msn;
    public final String emailAddress;
    public final Subscription subscription;

    public User(String title,
                String firstName,
                String lastName,
                String dateOfBirth,
                String contactNumber,
                String msn,
                String emailAddress,
                Subscription subscription) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.msn = msn;
        this.emailAddress = emailAddress;
        this.subscription = subscription;
    }
}
