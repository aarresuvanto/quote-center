package com.app.quotecenter;

import com.app.quotecenter.domain.Quote;
import com.app.quotecenter.domain.User;
import com.app.quotecenter.domain.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void saveUser() {
        int amountOfUsersDbStart = ((List<User>) userRepository.findAll()).size();

        User user = new User("first", "last", "testuser", "pword", "first.last@qc.io", "USER");
        userRepository.save(user);

        int amountOfUsersAfterSaveDb = ((List<User>) userRepository.findAll()).size();

        Assertions.assertEquals(amountOfUsersDbStart + 1, amountOfUsersAfterSaveDb, "One new user should be saved to the database");
    }

    @Test
    void validateUser() {
        User user = new User();
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user);
        });
    }

    @Test
    void findByUsername() {
        User user = userRepository.findByUsername("aarreolavi");
        Assertions.assertEquals("aarreolavi", user.getUsername(), "User should be found by username from DB");
    }

    @Test
    void deleteUser() {
        int amountOfUsersDbStart = ((List<User>) userRepository.findAll()).size();

        User user = new User("another", "user", "yes", "passwordd", "yes.yes@yes.io", "USER");
        userRepository.save(user);

        int amountOfUsersAfterSaveDb = ((List<User>) userRepository.findAll()).size();
        Assertions.assertEquals(amountOfUsersDbStart + 1, amountOfUsersAfterSaveDb, "One new user should be saved to the database");

        userRepository.delete(user);
        int amountOfUsersAfterDelete = ((List<User>) userRepository.findAll()).size();

        Assertions.assertEquals(amountOfUsersDbStart, amountOfUsersAfterDelete, "The added user should be deleted from the database");
    }

}
