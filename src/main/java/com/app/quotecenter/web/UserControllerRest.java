package com.app.quotecenter.web;

import com.app.quotecenter.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserControllerRest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuoteListRepository quoteListRepository;

    // Get all users in database
    @GetMapping("/api/users")
    public @ResponseBody List<User> getAllUsersInDb() {
        List<User> allUsersInDb = (List<User>) userRepository.findAll();
        return allUsersInDb;
    }

    // Get a user from database by id
    @GetMapping("/api/users/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") Long userId) {
        Optional<User> userById = userRepository.findById(userId);
        User userToReturn = userById.get();
        return userToReturn;
    }

    // Get all user quotelists from database
    @GetMapping("/api/users/{userId}/quotelists")
    public @ResponseBody List<QuoteList> getUserQuoteLists(@PathVariable("userId") Long userId) {
        User user = userRepository.findById(userId).get();
        List<QuoteList>userQuoteLists = quoteListRepository.findByUser(user);
        return userQuoteLists;
    }

}
