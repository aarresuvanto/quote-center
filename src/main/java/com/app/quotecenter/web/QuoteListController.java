package com.app.quotecenter.web;

import com.app.quotecenter.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuoteListController {

    @Autowired
    QuoteListRepository quoteListRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/newlist")
    public String newQuoteList(Model model) {
        QuoteList quotelist = new QuoteList();
        model.addAttribute("quotelist", quotelist);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();

        User currentUser = userRepository.findByUsername(currentUserUsername);

        List<Quote>userQuotesAll = currentUser.getQuotes();

        model.addAttribute("allquotes", userQuotesAll);

        return "newquotelist";
    }

    @PostMapping("/savelist")
    public String saveList(@ModelAttribute QuoteList quoteList) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);

        quoteList.setUser(currentUser);

        List<Quote>allQuotesUser = currentUser.getQuotes();
        List<Quote>toQuoteList = new ArrayList<Quote>();

        toQuoteList.add(allQuotesUser.get(1));
        toQuoteList.add(allQuotesUser.get(2));

        quoteList.setQuotes(toQuoteList);

        quoteListRepository.save(quoteList);

        return "redirect:/welcome";
    }

}
