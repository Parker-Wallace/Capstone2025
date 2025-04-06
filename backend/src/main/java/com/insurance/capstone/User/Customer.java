package com.insurance.capstone.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.insurance.capstone.Auto.AutoInsurance;
import com.insurance.capstone.Auto.AutoQuote;
import com.insurance.capstone.Core.Policy;
import com.insurance.capstone.Core.Quote;
import com.insurance.capstone.Home.Home;
import com.insurance.capstone.Home.HomeInsurance;
import com.insurance.capstone.Home.HomeQuote;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "customers")
public class Customer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;

    @OneToMany(mappedBy = "insuredPerson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Policy> policies = new ArrayList<>();

    @OneToMany(mappedBy = "insuredPerson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Quote> quotes = new ArrayList<>();


    protected Customer() {
        super();
    }

    public Customer(String username, int age) {
        super(username);
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    public void printQuotes() {
        System.out.println(getUsername() + "'s Quotes");
        System.out.println("________");
        if (quotes.isEmpty()) {
            System.out.println("No quotes available.");
            return;
        }

        for (Quote quote : quotes) {
            System.out.println("Quote ID: " + quote.getQuoteId());
            System.out.println("Start Date: " + quote.getStartDate());
            System.out.println("Base Premium: $" + quote.getBasePremium());
            System.out.println("Tax Rate: " + quote.getTaxRate());
            System.out.println("Total Premium: $" + String.format("%.2f", quote.getTotalPremium()));

            if (quote instanceof AutoQuote) {
                AutoQuote autoQuote = (AutoQuote) quote;
                System.out.println("Type: com.insurance.capstone.Auto Insurance Quote");
                System.out.println("Driver Age: " + autoQuote.getInsuredPerson().getAge());
                System.out.println("Accidents: " + autoQuote.getAccidents());
                System.out.println("Vehicle Make: " + autoQuote.getVehicleMake());
                System.out.println("Vehicle Model: " + autoQuote.getVehicleModel());
                System.out.println("Vehicle Year: " + autoQuote.getVehicleYear());
                System.out.println("com.insurance.capstone.Home Policy Discount: " + autoQuote.hasHomePolicyDiscount());
            } else if (quote instanceof HomeQuote) {
                HomeQuote homeQuote = (HomeQuote) quote;
                System.out.println("Type: com.insurance.capstone.Home Insurance Quote");
                System.out.println("Year Built: " + homeQuote.getYearBuilt());
                System.out.println("Dwelling Type: " + homeQuote.getDwellingType());
                System.out.println("Heating Type: " + homeQuote.getHeatingType());
                System.out.println("Location: " + homeQuote.getHome().getLocationType());
                System.out.println("com.insurance.capstone.Home Value: $" + homeQuote.getHomeValue());
                System.out.println("Liability Limit: " + homeQuote.getLiabilityLimit());
                System.out.println("com.insurance.capstone.Auto Policy Discount: " + homeQuote.hasAutoPolicyDiscount());
            }

            System.out.println("-----------------------------");
        }
    }

    public void addPolicy(Policy policy) {
        if (!policies.contains(policy)) {
            policies.add(policy);
        } else {
            System.out.println("Duplicate policy detected: " + policy.getPolicyId());
        }
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public boolean removeQuote(String quoteId) {
        Iterator<Quote> iterator = quotes.iterator();
        while (iterator.hasNext()) {
            Quote q = iterator.next();
            if (q.getQuoteId().equals(quoteId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void printPolicies() {
        System.out.println(getUsername() + "'s Policies");
        System.out.println("________");
        for (Policy policy : policies) {
            System.out.println("Policy ID: " + policy.getPolicyId());
            System.out.println("Start Date: " + policy.getStartDate());
            System.out.println("End Date: " + policy.getEndDate());
            System.out.println("Base Premium: $" + policy.getBasePremium());
            System.out.println("Tax Rate: " + policy.getTaxRate());
            System.out.println("Total Premium: $" + String.format("%.2f", policy.getTotalPremium()));

            if (policy instanceof AutoInsurance) {
                AutoInsurance autoPolicy = (AutoInsurance) policy;
                System.out.println("Type: com.insurance.capstone.Auto Insurance");
                System.out.println("Driver Age: " + autoPolicy.getDriverAge());
                System.out.println("Accidents: " + autoPolicy.getAccidents());
                System.out.println("Vehicle Make: " + autoPolicy.getVehicleMake());
                System.out.println("Vehicle Model: " + autoPolicy.getVehicleModel());
                System.out.println("Vehicle Year: " + autoPolicy.getVehicleYear());
                System.out.println("com.insurance.capstone.Home Policy Discount: " + autoPolicy.hasHomePolicyDiscount());
            } else if (policy instanceof HomeInsurance) {
                HomeInsurance homePolicy = (HomeInsurance) policy;
                Home insuredHome = homePolicy.getHome();
                System.out.println("Type: com.insurance.capstone.Home Insurance");
                System.out.println("Age Built: " + insuredHome.getYearBuilt());
                System.out.println("Dwelling Type: " + insuredHome.getDwellingType());
                System.out.println("Heating Type: " + insuredHome.getHeatingType());
                System.out.println("Location: " + insuredHome.getLocationType());
                System.out.println("com.insurance.capstone.Home Value: $" + insuredHome.getHomeValue());
                System.out.println("Liability Limit: " + insuredHome.getLiabilityLimit());
                System.out.println("com.insurance.capstone.Auto Policy Discount: " + homePolicy.hasAutoPolicyDiscount());
            }

            System.out.println("-----------------------------");
        }
    }

    public void addQuote(Quote quote) {
        if (!quotes.contains(quote)) {
            quotes.add(quote);
        } else {
            System.out.println("Duplicate quote detected: " + quote.getQuoteId());
        }
    }

    public List<Policy> getPolicies() {
        return policies;
    }
}