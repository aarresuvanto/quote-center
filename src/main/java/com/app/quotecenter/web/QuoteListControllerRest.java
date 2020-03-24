package com.app.quotecenter.web;

import com.app.quotecenter.domain.Quote;
import com.app.quotecenter.domain.QuoteList;
import com.app.quotecenter.domain.QuoteListRepository;
import com.app.quotecenter.domain.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class QuoteListControllerRest {

    @Autowired
    QuoteListRepository quoteListRepository;

    @Autowired
    QuoteRepository quoteRepository;

    Random rand = new Random();

    // Get a quotelist by id from database
    @GetMapping("/api/quotelists/{id}")
    public @ResponseBody Optional<QuoteList> quoteListByListId(@PathVariable("id") Long listId) {
        Optional<QuoteList> quoteList = quoteListRepository.findById(listId);
        return quoteList;
    }

    // Get one random Quote from desired quotelist (id)
    @GetMapping("/api/quotelists/{id}/random")
    public @ResponseBody Quote randomQuoteFromQuotelist(@PathVariable("id") Long listId) {
        Optional<QuoteList> quoteList = quoteListRepository.findById(listId);

        int amountOfQuotesOnQuoteList = quoteList.get().getQuotes().size();
        int random = rand.nextInt(amountOfQuotesOnQuoteList);

        List<Quote>quotesOnList = quoteRepository.findByQuoteList(quoteList.get());
        Quote chosen = quotesOnList.get(random);

        return chosen;
    }

    // Get all quotelists from database
    @GetMapping("/api/quotelists")
    public @ResponseBody List<QuoteList>allQuoteListsInDb() {
        List<QuoteList> allQuoteLists = (List<QuoteList>) quoteListRepository.findAll();
        return allQuoteLists;
    }

}
