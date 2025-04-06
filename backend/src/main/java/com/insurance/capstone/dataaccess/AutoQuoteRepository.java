package com.insurance.capstone.dataaccess;

import com.insurance.capstone.Auto.AutoQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoQuoteRepository extends JpaRepository<AutoQuote, Long> {
    AutoQuote findByQuoteId(String quoteId);
}