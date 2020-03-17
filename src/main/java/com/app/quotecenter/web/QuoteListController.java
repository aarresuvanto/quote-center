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

    @Autowired
    QuoteRepository quoteRepository;

    // When crete a new quotelist is clicked in welcome.html
    // New QuoteList object is created and is added to model
    // The user is redirected to newquotelist.html view
    @GetMapping("/newlist")
    public String newQuoteList(Model model) {
        QuoteList quotelist = new QuoteList();
        model.addAttribute("quotelist", quotelist);
        Quote quote = new Quote();
        model.addAttribute("quote", quote);

        return "newquotelist";
    }

    // When clicking create new list in newquotelist.html
    // The username of the current user is fetched
    // The currentUser object is added to the QuoteList object received from the view
    // The new QuoteList object is then saved to the h2 database
    @PostMapping("/savelist")
    public String saveList(@ModelAttribute QuoteList quoteList) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);

        quoteList.setUser(currentUser);

        List<Quote> quotesOnQuoteList = quoteList.getQuotes();

        for(int i = 0; i < quotesOnQuoteList.size(); i++) {
            Quote current = quotesOnQuoteList.get(i);
            current.setUser(currentUser);
            current.setQuoteList(quoteList);
        }

        quoteListRepository.save(quoteList);

        return "redirect:/welcome";
    }

    @GetMapping("/userquotelists")
    public String getUserQuoteLists(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);

        // Get current user QuoteLists
        List<QuoteList>currentUserQuoteLists = quoteListRepository.findByUser(currentUser);
        for(int i = 0; i < currentUserQuoteLists.size(); i++) {
            System.out.println(currentUserQuoteLists.get(i).getListName());
            System.out.println("");
        }

        model.addAttribute("userquotelists", currentUserQuoteLists);

        return "quotelistsuser";
    }

}
