package com.app.quotecenter.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteListRepository extends CrudRepository<QuoteList, Long> {
    List<QuoteList> findByUser(User user);
}
