package com.app.quotecenter.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.CascadingAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    @Column(nullable = false, unique = true)
    private long userId;

    @JsonIgnore
    @Column(nullable = false)
    private String firstName;

    @JsonIgnore
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name="password_hash", nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false, unique = true)
    private String eMail;

    @JsonIgnore
    @Column(nullable = false)
    private String role = "USER";

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Column(nullable = false)
    private List<Quote>quotes;

    // List of quote lists
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Column(nullable = false)
    private List<QuoteList>quoteLists;

    public User() {}

    // Remove role from constructor before deploying
    public User(String firstName, String lastName, String username, String password, String eMail, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.eMail = eMail;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(long userId) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", role='" + role + '\'' +
                ", quotes=" + quotes +
                '}';
    }
}


