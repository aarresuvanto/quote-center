package com.app.quotecenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    @Column(nullable = false, unique = true)
    private long quoteId;

    @NotBlank(message = "Must not be blank")
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="listId")
    private QuoteList quoteList;

    public Quote() {}

    public Quote(String text) {
        this.text = text;
    }

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

    public QuoteList getQuoteList() {
        return quoteList;
    }

    public void setQuoteList(QuoteList quoteList) {
        this.quoteList = quoteList;
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
