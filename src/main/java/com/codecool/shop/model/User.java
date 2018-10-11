package com.codecool.shop.model;

public class User {

    private String name;
    private String email;
    private String password;
    private String id;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
