package com.app.quotecenter;

import com.app.quotecenter.web.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuotecenterApplicationTests {

	@Autowired
	QuoteController quoteController;

	@Autowired
	QuoteControllerRest quoteControllerRest;

	@Autowired
	QuoteListController quoteListController;

	@Autowired
	QuoteListControllerRest quoteListControllerRest;

	@Autowired
	UserController userController;

	@Autowired
	UserControllerRest userControllerRest;

	@Test
	void QuoteControllerLoad() throws Exception {
		Assertions.assertThat(quoteController).isNotNull();
	}

	@Test
	void QuoteControllerRestLoad() throws Exception {
		Assertions.assertThat(quoteControllerRest).isNotNull();
	}

	@Test
	void QuoteListControllerLoad() throws Exception {
		Assertions.assertThat(quoteListController).isNotNull();
	}

	@Test
	void QuoteListControllerRestLoad() throws Exception {
		Assertions.assertThat(quoteListControllerRest).isNotNull();
	}

	@Test
	void UserControllerLoad() throws Exception {
		Assertions.assertThat(userController).isNotNull();
	}

	@Test
	void UserControllerRestLoad() throws Exception {
		Assertions.assertThat(userControllerRest).isNotNull();
	}

}
