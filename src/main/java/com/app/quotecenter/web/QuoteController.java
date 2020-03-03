package com.app.quotecenter.web;

import com.app.quotecenter.domain.Quote;
import com.app.quotecenter.domain.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/newquote")
    public String newQuoteForm(Model model) {
        model.addAttribute("quote", new Quote());
        model.addAttribute("quotes", quoteRepository.findAll());
        return "newquote";
    }

    @PostMapping("/savequote")
    public String saveQuote(@ModelAttribute Quote quote) {
        quoteRepository.save(quote);
        List<Quote>quotes = (List<Quote>) quoteRepository.findAll();
        return"redirect:/newquote";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuote(@PathVariable("id") Long quoteId, Model model) {
        quoteRepository.deleteById(quoteId);
        return "redirect:/newquote";
    }
}
