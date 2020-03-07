package com.app.quotecenter;

import com.app.quotecenter.domain.Quote;
import com.app.quotecenter.domain.QuoteRepository;
import com.app.quotecenter.domain.User;
import com.app.quotecenter.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class QuotecenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotecenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, QuoteRepository quoteRepository) {
		return(args) -> {
			User user = new User("Aarre", "Suvanto", "aarreolavi", "$2y$12$avwWO0O7bN901XUjrvb3vOG2UW4t0zDf3baPBJ8xxKvjJi6GmaYRa", "aarre.suvanto@gmail.com", "USER");
			Quote quote = new Quote("In flux of fortune remain calm, for praise is neither good nor bad", user);
			userRepository.save(user);
			quoteRepository.save(quote);
		};
	}

}
