package com.app.quotecenter.web;

import com.app.quotecenter.domain.Quote;
import com.app.quotecenter.domain.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuoteControllerRest {

    @Autowired
    QuoteRepository quoteRepository;

    // Get all quotes from database
    @GetMapping("/api/quotes")
    public @ResponseBody List<Quote> allQuotes() {
        List<Quote>allQuotesInDb = (List<Quote>) quoteRepository.findAll();
        return allQuotesInDb;
    }

    // Get a quote from database based on quote id
    @GetMapping("/api/quotes/{id}")
    public @ResponseBody Optional<Quote> quoteById(@PathVariable("id") Long quoteId) {
        return quoteRepository.findById(quoteId);
    }

}
