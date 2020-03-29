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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/welcome")
    public String showWelcomeView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();

        model.addAttribute("username", currentUserUsername);

        return "welcome";
    }

    @GetMapping("/newquote")
    public String newQuoteForm(Model model) {
        model.addAttribute("quote", new Quote());
        model.addAttribute("quotes", quoteRepository.findAll());
        return "newquote";
    }

    @GetMapping("/allquotes")
    public String showAllQuotes(Model model) {
        model.addAttribute("quotes", quoteRepository.findAll());
        return "allquotes";
    }

    @PostMapping("/savequote")
    public String saveQuote(@ModelAttribute @Valid Quote quote, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "newquote";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);
        quote.setUser(currentUser);
        quoteRepository.save(quote);
        return"redirect:/welcome";
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
