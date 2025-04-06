package com.insurance.capstone.dataaccess;

import com.insurance.capstone.Home.HomeQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeQuoteRepository extends JpaRepository<HomeQuote, Long> {
    HomeQuote findByQuoteId(String quoteId);
}
