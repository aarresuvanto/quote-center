package com.app.quotecenter.web;

import com.app.quotecenter.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuoteListController {

    @Autowired
    QuoteListRepository quoteListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/newlist")
    public String newQuoteList(Model model) {
        model.addAttribute("quotelist", new QuoteList());

        return "newquotelist";
    }

    @PostMapping("/savelist")
    public String saveList(@ModelAttribute("quotelist") @Valid QuoteList quoteList, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "newquotelist";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);

        // Set quotelist user
        quoteList.setUser(currentUser);

        List<Quote> quotesOnQuoteList = quoteList.getQuotes();

        for(int i = 0; i < quotesOnQuoteList.size(); i++) {
            Quote current = quotesOnQuoteList.get(i);
            current.setUser(currentUser);
            current.setQuoteList(quoteList);
        }

        quoteListRepository.save(quoteList);

        return "redirect:/profile";
    }

    @GetMapping("/userquotelists")
    public String getUserQuoteLists(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        User currentUser = userRepository.findByUsername(currentUserUsername);

        List<QuoteList>currentUserQuoteLists = quoteListRepository.findByUser(currentUser);

        model.addAttribute("userquotelists", currentUserQuoteLists);

        return "quotelistsuser";
    }

    @GetMapping("/quotelist/{id}")
    public String getSpecificQuoteList(@PathVariable("id") Long listId, Model model) {
        Optional<QuoteList> quoteList = quoteListRepository.findById(listId);

        List<Quote>quotesOnList = quoteRepository.findByQuoteList(quoteList.get());
        String listName = quoteList.get().getListName();

        String listRestUrl = "http://localhost:8080/api/quotelists/" + listId.toString();

        model.addAttribute("listname", listName);
        model.addAttribute("listquotes", quotesOnList);
        model.addAttribute("listresturl", listRestUrl);

        return "quotelistuserdetailed";
    }

    @GetMapping("/allquotelists")
    public String getAllQuoteLists(Model model) {
        Iterable<QuoteList>allQuoteLists = quoteListRepository.findAll();

        String listRestUrlBase = "http://localhost:8080/api/quotelists/";

        model.addAttribute("baseurl", listRestUrlBase);
        model.addAttribute("allquotelists", allQuoteLists);
        return "allquotelists";
    }

}
