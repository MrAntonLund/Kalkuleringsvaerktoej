package com.examprojectsummer2021.models;

/**
 * @author Julius
 */

public class User {

    private String username;
    private String firstname;
    private String lastname;
    private String role;
    private int salary;

    public User(String username, String firstname, String lastname, String role, int salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.role = role;
        this.salary = salary;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public int getSalary(){return salary;}

    //Made to get the fully user information, to reduce the numbers of method calls.
    public String getFullUserInfo() {
        return firstname + lastname + username + role;
    }

}
