package com.app.quotecenter.web;

import com.app.quotecenter.domain.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuoteController {

    @GetMapping("/newquote")
    public String newQuoteForm(Model model) {
        model.addAttribute("quote", new Quote());
        return "newquote";
    }

    @PostMapping("/savequote")
    public String saveQuote(@ModelAttribute Quote quote, Model model) {
        model.addAttribute("quote", quote);
        System.out.println(quote);
        return"redirect:/newquote";
    }
}
