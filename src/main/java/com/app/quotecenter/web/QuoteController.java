package com.app.quotecenter.web;

import com.app.quotecenter.domain.Quote;
import com.app.quotecenter.domain.QuoteRepository;
import com.app.quotecenter.domain.User;
import com.app.quotecenter.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String welcomeScreen() {
        return "welcome";
    }

    @GetMapping("/newquote")
    public String newQuoteForm(Model model) {
        model.addAttribute("quote", new Quote());
        model.addAttribute("quotes", quoteRepository.findAll());
        return "newquote";
    }

    @PostMapping("/savequote")
    public String saveQuote(@ModelAttribute Quote quote) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);
        quote.setUser(currentUser);
        quoteRepository.save(quote);
        return"redirect:/newquote";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuote(@PathVariable("id") Long quoteId, Model model) {
        quoteRepository.deleteById(quoteId);
        return "redirect:/newquote";
    }

    @GetMapping("/edit/{id}")
    public String editQuote(@PathVariable("id") Long quoteId, Model model) {
        model.addAttribute("quote", quoteRepository.findById(quoteId));
        return "editquote";
    }
}
