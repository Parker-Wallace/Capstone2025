package com.insurance.capstone.Controllers;

import com.insurance.capstone.Auto.AutoQuote;
import com.insurance.capstone.Auto.Automobile;
import com.insurance.capstone.Core.QuoteFactory;
import com.insurance.capstone.User.Customer;
import com.insurance.capstone.dataaccess.CustomerRepository;
import com.insurance.capstone.dataaccess.AutoQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/autoquotes")
public class AutoQuoteController {

    @Autowired
    private AutoQuoteRepository autoQuoteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private QuoteFactory quoteFactory;

    @PostMapping
    public ResponseEntity<AutoQuote> createAutoQuote(@RequestBody Map<String, Object> payload) {
        try {
            String quoteId = (String) payload.get("quoteId");
            LocalDate startDate = LocalDate.parse((String) payload.get("startDate"));
            boolean hasHomePolicyDiscount = Boolean.parseBoolean(payload.get("hasHomePolicyDiscount").toString());

            Map<String, Object> autoData = (Map<String, Object>) payload.get("automobile");
            String make = (String) autoData.get("vehicleMake");
            String model = (String) autoData.get("vehicleModel");
            int year = (int) autoData.get("vehicleYear");
            int accidents = (int) autoData.get("numberofAccidents");

            Automobile auto = new Automobile(make, model, year, accidents);

            long customerId = Long.parseLong(payload.get("customerId").toString());
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalArgumentException("Customer ID not found"));

            AutoQuote quote = quoteFactory.createAutoQuote(
                    quoteId, customer, startDate, hasHomePolicyDiscount, auto
            );

            AutoQuote saved = autoQuoteRepository.save(quote);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity<String> deleteAutoQuote(@PathVariable String quoteId) {
        AutoQuote quote = autoQuoteRepository.findByQuoteId(quoteId);
        if (quote != null) {
            autoQuoteRepository.delete(quote);
            return ResponseEntity.ok("AutoQuote deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("AutoQuote not found.");
        }
    }

}
