package com.insurance.capstone.User;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    @Column(nullable = false, unique = true)
    private String username;

    protected User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public abstract String getRole();
}
