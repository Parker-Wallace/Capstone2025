package com.insurance.capstone.dataaccess;

import com.insurance.capstone.Core.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Quote findByQuoteId(String quoteId);
}
