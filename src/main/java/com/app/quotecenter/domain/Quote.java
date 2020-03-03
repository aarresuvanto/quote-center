package com.app.quotecenter.domain;

public class Quote {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "text='" + text + '\'' +
                '}';
    }
}
