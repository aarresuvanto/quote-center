package com.app.quotecenter.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.List;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long quoteId;
    private String text;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="listId")
    private QuoteList quoteList;

    public Quote() {}

    public Quote(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Quote(String text, User user, QuoteList quoteList) {
        this.text = text;
        this.user = user;
        this.quoteList = quoteList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
