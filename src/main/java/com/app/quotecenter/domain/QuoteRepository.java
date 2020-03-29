package com.app.quotecenter.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
    List<Quote>findByQuoteList(QuoteList quoteList);
    List<Quote>findByUser(User user);
}
