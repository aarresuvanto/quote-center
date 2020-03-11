package com.app.quotecenter.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class QuoteList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long listId;
    private String listName;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Quote>quotes;

    public QuoteList() {}

    public QuoteList(String listName, User user) {
        this.listName = listName;
        this.user = user;
    }

    public QuoteList(String listName, User user, List<Quote>quotes) {
        this.listName = listName;
        this.user = user;
        this.quotes = quotes;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "QuoteList{" +
                "listId=" + listId +
                ", listName='" + listName + '\'' +
                ", user=" + user +
                ", quotes=" + quotes +
                '}';
    }
}
