package com.app.quotecenter.web;

import com.app.quotecenter.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    QuoteListRepository quoteListRepository;

    @GetMapping("/newprofile")
    public String newProfileForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();

        User currentUser = userRepository.findByUsername(currentUserUsername);
        ArrayList<Quote>quotesToModel = (ArrayList<Quote>) quoteRepository.findByUser(currentUser);

        List<QuoteList>userQuoteLists = quoteListRepository.findByUser(currentUser);

        int amountOfUserQuoteLists = userQuoteLists.size();
        int amountOfUserQuotes = quotesToModel.size();

        model.addAttribute("userquotes", quotesToModel);
        model.addAttribute("user", currentUser);
        model.addAttribute("amountOfQuoteLists", amountOfUserQuoteLists);
        model.addAttribute("amountOfQuotes", amountOfUserQuotes);

        return "userprofile";
    }

    @PostMapping("/saveuser")
    public String saveProfile(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "redirect:/welcome";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @GetMapping("/signout")
    public String signOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/welcome";
    }

    @GetMapping("/success")
    public String signInSuccess() {
        return "success";
    }

}
