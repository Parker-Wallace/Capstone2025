package com.insurance.capstone.Controllers;

import com.insurance.capstone.Core.Quote;
import com.insurance.capstone.User.Customer;
import com.insurance.capstone.User.Representative;
import com.insurance.capstone.Core.Policy;
import com.insurance.capstone.dataaccess.*;
import com.insurance.capstone.Auto.AutoQuote;
import com.insurance.capstone.Home.HomeQuote;
import com.insurance.capstone.dataaccess.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/representatives")
public class RepresentativeController {

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AutoQuoteRepository autoQuoteRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private HomeQuoteRepository homeQuoteRepository;

    @PostMapping("/{repId}/convert-quote")
    public ResponseEntity<?> convertQuoteToPolicy(@PathVariable Long repId, @RequestBody Map<String, Object> payload) {
        try {
            System.out.println("Received request to convert quote with ID: " + payload.get("quoteId") + " for customer ID: " + payload.get("customerId"));

            long customerId = Long.parseLong(payload.get("customerId").toString());
            String quoteId = payload.get("quoteId").toString();

            Representative representative = representativeRepository.findById(repId)
                    .orElseThrow(() -> new IllegalArgumentException("Representative not found"));

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

            Quote selectedQuote = customer.getQuotes().stream()
                    .filter(q -> q.getQuoteId().trim().equalsIgnoreCase(quoteId.trim()))
                    .findFirst()
                    .orElse(null);

            if (selectedQuote == null) {
                System.out.println("Quote ID " + quoteId + " not found for Customer ID " + customerId);
                return ResponseEntity.badRequest().body("Quote not found or already converted.");
            }

            representative.convertQuoteToPolicy(customer, quoteId);
            customerRepository.save(customer);

            if (selectedQuote instanceof HomeQuote) {
                HomeQuote toDelete = homeQuoteRepository.findByQuoteId(quoteId);
                if (toDelete != null) {
                    homeQuoteRepository.delete(toDelete);
                    System.out.println("Deleted HomeQuote from database: " + toDelete.getQuoteId());
                }
            } else if (selectedQuote instanceof AutoQuote) {
                AutoQuote toDelete = autoQuoteRepository.findByQuoteId(quoteId);
                if (toDelete != null) {
                    autoQuoteRepository.delete(toDelete);
                    System.out.println("Deleted AutoQuote from database: " + toDelete.getQuoteId());
                }
            }

            System.out.println("Quote successfully converted and cleaned up.");
            return ResponseEntity.ok("Policy creation successful.");

        } catch (Exception e) {
            System.out.println("Error during quote conversion: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Conversion failed: " + e.getMessage());
        }
    }

    @PostMapping
    public Representative createRepresentative(@RequestBody Representative representative) {
        return representativeRepository.save(representative);
    }

    @GetMapping("/{id}")
    public Representative getRepresentative(@PathVariable Long id) {
        return representativeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Representative> getAllRepresentatives() {
        return representativeRepository.findAll();
    }
}
