package com.company;

public class User {

    String userName;
    Role userRole;

    User(String userName, Role role){
        this.userName = userName;
        this.userRole = role;
    }

    public Role getUserRole() {
        return userRole;
    }

    public String getUserName() {
        return userName;
    }
}
