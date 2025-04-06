package com.insurance.capstone.Controllers;

import com.insurance.capstone.Core.QuoteFactory;
import com.insurance.capstone.Home.Home;
import com.insurance.capstone.Home.HomeQuote;
import com.insurance.capstone.User.Customer;
import com.insurance.capstone.dataaccess.CustomerRepository;
import com.insurance.capstone.dataaccess.HomeQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/homequotes")
public class HomeQuoteController {

    @Autowired
    private HomeQuoteRepository homeQuoteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private QuoteFactory quoteFactory;

    @PostMapping
    public ResponseEntity<HomeQuote> createHomeQuote(@RequestBody Map<String, Object> payload) {
        try {
            String quoteId = (String) payload.get("quoteId");
            LocalDate startDate = LocalDate.parse((String) payload.get("startDate"));
            boolean hasAutoPolicyDiscount = Boolean.parseBoolean(payload.get("hasAutoPolicyDiscount").toString());

            Map<String, Object> homeData = (Map<String, Object>) payload.get("home");
            int yearBuilt = (int) homeData.get("yearBuilt");
            double homeValue = Double.parseDouble(homeData.get("homeValue").toString());
            double liabilityLimit = Double.parseDouble(homeData.get("liabilityLimit").toString());
            String dwellingType = (String) homeData.get("dwellingType");
            String heatingType = (String) homeData.get("heatingType");
            String locationType = (String) homeData.get("locationType");

            Home home = new Home(yearBuilt, homeValue, liabilityLimit, dwellingType, heatingType, locationType);

            long customerId = Long.parseLong(payload.get("customerId").toString());
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalArgumentException("Customer ID not found"));

            HomeQuote quote = quoteFactory.createHomeQuote(
                    quoteId, customer, startDate, home, hasAutoPolicyDiscount
            );

            HomeQuote saved = homeQuoteRepository.save(quote);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity<String> deleteHomeQuote(@PathVariable String quoteId) {
        HomeQuote quote = homeQuoteRepository.findByQuoteId(quoteId);
        if (quote != null) {
            homeQuoteRepository.delete(quote);
            return ResponseEntity.ok("HomeQuote deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("HomeQuote not found.");
        }
    }


}
