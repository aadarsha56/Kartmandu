package com.example.kartmandu.Model;

public class Authtoken {
    private String token;
    private User users;

    public Authtoken(String token, User users) {
        this.token = token;
        this.users = users;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
