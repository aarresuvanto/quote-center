package com.app.quotecenter.domain;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quoteId;
    private String text;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Quote() {}

    public Quote(long quoteId, String text, User user) {
        this.quoteId = quoteId;
        this.text = text;
        this.user = user;
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
