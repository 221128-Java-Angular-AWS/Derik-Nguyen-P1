package com.revature.pojos;

import java.util.HashMap;
import java.util.Objects;
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isManager;

    //private String time_created;
    //no args: constructor:
    public User(){
    }

    public User(int userId, String firstName, String lastName, String username, String password, Boolean isManager){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    public User(String firstName, String lastName, String username, String password, String title){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(isManager, user.isManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, username, password, isManager);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
