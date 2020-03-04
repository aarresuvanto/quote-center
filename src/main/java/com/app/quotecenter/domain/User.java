package com.app.quotecenter.domain;

import com.sun.org.apache.xpath.internal.operations.Quo;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String eMail;

    // Connecting user and quotes

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Quote>quotes;

    public User() {}

    public User(String firstName, String lastName, String username, String passwordHash, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.eMail = eMail;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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
                ", passwordHash='" + passwordHash + '\'' +
                ", eMail='" + eMail + '\'' +
                ", quotes=" + quotes +
                '}';
    }
}


