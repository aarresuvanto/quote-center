package com.app.quotecenter.web;

import com.app.quotecenter.domain.User;
import com.app.quotecenter.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/newprofile")
    public String newProfileForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/saveuser")
        public String saveProfile(@ModelAttribute User user) {
            userRepository.save(user);
            System.out.println(userRepository.findAll());
            return "redirect:/newquote";
        }

}
