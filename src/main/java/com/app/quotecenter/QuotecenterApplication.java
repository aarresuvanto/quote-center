package com.app.quotecenter;

import com.app.quotecenter.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuotecenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotecenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, QuoteRepository quoteRepository, QuoteListRepository quoteListRepository) {
		return(args) -> {
			User user = new User("Aarre", "Suvanto", "aarreolavi", "$2y$12$avwWO0O7bN901XUjrvb3vOG2UW4t0zDf3baPBJ8xxKvjJi6GmaYRa", "aarre.suvanto@gmail.com", "USER");

			Quote firstQuote = new Quote("In flux of fortune remain calm, for praise is neither good nor bad", user);
			Quote secondQuote = new Quote ("I have found that if you love life, life will love you back", user);
			Quote thirdQuote = new Quote("Each morning we are born again. What we do today is what matters most", user);
			Quote fourthQuote = new Quote("The greatest glory in living lies not in never falling, but rising every time we fail", user);

			userRepository.save(user);

			quoteRepository.save(firstQuote);
			quoteRepository.save(secondQuote);
			quoteRepository.save(thirdQuote);
			quoteRepository.save(fourthQuote);

		};
	}

}
