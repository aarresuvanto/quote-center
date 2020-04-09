package com.app.quotecenter;

import com.app.quotecenter.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

@SpringBootTest
public class QuoteListTests {

    @Autowired
    QuoteListRepository quoteListRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void saveQuoteList() {
        List<User>usersInDb = (List<User>) userRepository.findAll();
        User user = usersInDb.get(0);

        List<QuoteList>quoteListsInDb = (List<QuoteList>) quoteListRepository.findAll();
        int amountOfListsInDbStart = quoteListsInDb.size();

        QuoteList quoteList = new QuoteList("firstQuoteList", user);
        quoteListRepository.save(quoteList);

        int amountOfListsAfterSaveDb = ((List<QuoteList>) quoteListRepository.findAll()).size();
        Assertions.assertEquals(amountOfListsInDbStart + 1, amountOfListsAfterSaveDb, "A new QuoteList should be saved to the database");
    }

    @Test
    void deleteQuoteList() {
        List<User>usersInDb = (List<User>) userRepository.findAll();
        User user = usersInDb.get(0);

        List<QuoteList>quoteListsInDb = (List<QuoteList>) quoteListRepository.findAll();
        int amountOfListsInDbStart = quoteListsInDb.size();

        QuoteList quoteList = new QuoteList("firstQuoteList", user);
        quoteListRepository.save(quoteList);

        int amountOfListsAfterSaveDb = ((List<QuoteList>) quoteListRepository.findAll()).size();
        Assertions.assertEquals(amountOfListsInDbStart + 1, amountOfListsAfterSaveDb, "A new QuoteList should be saved to the database");

        quoteListRepository.delete(quoteList);
        int amountOfListsAfterDelete = ((List<QuoteList>) quoteListRepository.findAll()).size();

        Assertions.assertEquals(amountOfListsInDbStart, amountOfListsAfterDelete, "The added QuoteList should now be deleted");
    }

    @Test
    void findQuoteListsByUser() {
        List<User>usersInDb = (List<User>) userRepository.findAll();
        User user = usersInDb.get(0);

        String username = user.getUsername();

        List<QuoteList> quoteLists = (List<QuoteList>) quoteListRepository.findByUser(user);
        Assertions.assertEquals(username, quoteLists.get(0).getUser().getUsername(), "QuoteLists can be searched by User");
    }

    @Test
    void validateQuoteList() {
        QuoteList quoteList = new QuoteList();
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            quoteListRepository.save(quoteList);
        });
    }

}
