package com.insurance.capstone.User;
import java.time.LocalDate;
import java.util.Iterator;
import com.insurance.capstone.Auto.AutoInsurance;
import com.insurance.capstone.Auto.AutoQuote;
import com.insurance.capstone.Auto.Automobile;
import com.insurance.capstone.Core.Policy;
import com.insurance.capstone.Core.PolicyFactory;
import com.insurance.capstone.Core.Quote;
import com.insurance.capstone.Home.Home;
import com.insurance.capstone.Home.HomeInsurance;
import com.insurance.capstone.Home.HomeQuote;
import jakarta.persistence.*;

//TODO Clean up debugging text

@Entity
@Table(name = "representatives")
public class Representative extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Representative() {
        super();
    }
    public Representative(String username) {
        super(username);
    }

    public void convertQuoteToPolicy(Customer customer, String quoteId) {
        Iterator<Quote> iterator = customer.getQuotes().iterator();
        Quote selectedQuote = null;

        System.out.println("\n[convertQuoteToPolicy] Start of the conversion process for Quote ID: " + quoteId);
        System.out.println("Customer: " + customer.getUsername() + " (ID: " + customer.getId() + ")");
        System.out.println("Customer has " + customer.getQuotes().size() + " quote(s):");
        customer.getQuotes().forEach(q -> System.out.println("  - " + q.getQuoteId() + " (" + q.getClass().getSimpleName() + ")"));

        while (iterator.hasNext()) {
            Quote quote = iterator.next();
            if (quote.getQuoteId().equals(quoteId)) {
                selectedQuote = quote;
                iterator.remove();
                System.out.println("Quote ID " + quoteId + " found and removed from customer " + customer.getUsername());
                break;
            }
        }

        if (selectedQuote != null) {
            Policy policy = PolicyFactory.createPolicyFromQuote(selectedQuote, LocalDate.now());
            if (policy != null) {
                customer.addPolicy(policy);
                System.out.println("Policy created: " + policy.getPolicyId() + " and added to customer.");
            } else {
                System.out.println("Policy creation failed for quote: " + quoteId);
            }
        } else {
            System.out.println("Quote ID " + quoteId + " not found or already removed.");
        }

        System.out.println("[convertQuoteToPolicy] End of method.\n");
    }


    private Policy createPolicyFromQuote(Quote quote) {
        LocalDate policyStart = LocalDate.now();

        if (quote instanceof AutoQuote autoQuote) {
            Automobile auto = autoQuote.getAutomobile();
            Customer insured = autoQuote.getInsuredPerson();
            return new AutoInsurance(
                    "AUTO-" + autoQuote.getQuoteId(),
                    insured,
                    policyStart,
                    autoQuote.getBasePremium(),
                    autoQuote.getTaxRate(),
                    autoQuote.getTotalPremium(),
                    autoQuote.hasHomePolicyDiscount(),
                    auto,
                    null
            );
        } else if (quote instanceof HomeQuote homeQuote) {
            Home home = homeQuote.getHome();

            return new HomeInsurance(
                    "HOME-" + homeQuote.getQuoteId(),
                    homeQuote.getInsuredPerson(),
                    policyStart,
                    homeQuote.getBasePremium(),
                    homeQuote.getTaxRate(),
                    homeQuote.getTotalPremium(),
                    home,
                    homeQuote.hasAutoPolicyDiscount(),
                    null
            );
        }

        return null;
    }

    @Override
    public String getRole() {
        return "Representative";
    }
}