package org.example.pojos.Core;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



/**
 * Represents a User Entity with shared attributes between a Customer and Representative
 * 
 * @author Parker Wallace
 */
@Entity
public class User {

    public enum Role {
        REPRESENTATIVE,
        CUSTOMER
    }

    /**
     * The unique identifier for this User.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    
    private String email;
    
    private LocalDate dateOfBirth;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean activeStatus;

    /**
     * Default contructor required by JPA.
     */
    public User() {}
    
    /**
     * Constructs a new User object with specified attributes.
     * @param username the username for the user.
     * @param email the email contact for the user.
     */
    public User(String username, String email, String password, LocalDate dateOfBirth) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
    
    // GETTERS AND SETTERS

    /**
     * Gets the unique identifier of this User.
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the username for This User.
     * @return This User's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the Email contact for This User
     * @return This User's email.
     */
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Role getRole() {
        return this.role;
    }

    /**
     * Sets this User's email property.
     * @param email The new email for this User.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets this User's username Property
     * @param username The new username for this User.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getActiveStatus() {
        return this.activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
