package com.app.quotecenter;

import com.app.quotecenter.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

@SpringBootTest
public class QuoteTests {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuoteListRepository quoteListRepository;

    @Test
    void saveQuote() {
        List<User>usersInDb = (List<User>) userRepository.findAll();
        List<QuoteList>listsInDb = (List<QuoteList>) quoteListRepository.findAll();

        User user = usersInDb.get(0);
        QuoteList quoteList = listsInDb.get(0);

        Quote text = new Quote("TestQuoteOne");
        Quote textUser = new Quote("TestQuoteTwo", user);
        Quote textUserQuoteList = new Quote("TestQuoteThree", user, quoteList);

        int quotesBeforeSave = ((List<Quote>) quoteRepository.findAll()).size();

        quoteRepository.save(text);
        quoteRepository.save(textUser);
        quoteRepository.save(textUserQuoteList);

        int quotesAfterSave = ((List<Quote>) quoteRepository.findAll()).size();

        Assertions.assertEquals(quotesBeforeSave + 3, quotesAfterSave, "Three new Quotes should be added to the database. Each with different parameters.");
    }

    @Test
    void deleteQuote() {
        int quoteAmountAtBeginning = ((List<Quote>) quoteRepository.findAll()).size();

        Quote quote = new Quote("testext");
        quoteRepository.save(quote);

        int quoteAmountAfterSave = ((List<Quote>) quoteRepository.findAll()).size();

        Assertions.assertEquals(quoteAmountAtBeginning + 1, quoteAmountAfterSave, "One new Quote should be saved to the DB");

        quoteRepository.delete(quote);
        int quoteAmountAfterDelete = ((List<Quote>) quoteRepository.findAll()).size();

        Assertions.assertEquals(quoteAmountAtBeginning, quoteAmountAfterDelete, "The Quote that was added before should be deleted");
    }

    @Test
    void validateQuote() {
        Quote quote = new Quote();
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            quoteRepository.save(quote);
        });
    }

    @Test
    void getAllQuotesFromDB() {
        int amountOfQuotesDB = ((List<Quote>) quoteRepository.findAll()).size();
        Boolean fourOrMore = false;

        if(amountOfQuotesDB >= 4) {
            fourOrMore = true;
        }

        Assertions.assertEquals(amountOfQuotesDB >= 4, fourOrMore, "There should be at least four Quotes in the DB");
    }

}
