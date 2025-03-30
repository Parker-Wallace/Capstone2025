package pojos.User;
import java.time.LocalDate;
import java.util.Iterator;

import pojos.Auto.AutoInsurance;
import pojos.Auto.AutoQuote;
import pojos.Core.Policy;
import pojos.Core.Quote;
import pojos.Core.User;
import pojos.Home.HomeInsurance;
import pojos.Home.HomeQuote;

public class Representative extends User {
    public Representative(String username) {
        super(username);
    }

    public void convertQuoteToPolicy(Customer customer, String quoteId) {
        Iterator<Quote> iterator = customer.getQuotes().iterator();
        Quote selectedQuote = null;

        while (iterator.hasNext()) {
            Quote quote = iterator.next();
            if (quote.getQuoteId().equals(quoteId)) {
                selectedQuote = quote;
                iterator.remove();
                break;
            }
        }

        if (selectedQuote != null) {
            Policy policy = createPolicyFromQuote(selectedQuote);
            if (policy != null) {
                System.out.println("Converted Quote ID: " + quoteId + " to Policy ID: " + policy.getPolicyId());
            }
        } else {
            System.out.println("Quote ID " + quoteId + " not found or already converted.");
        }
    }

    private Policy createPolicyFromQuote(Quote quote) {
        return null;
    }



    @Override
    public String getRole() {
        return "Representative";
    }
}
