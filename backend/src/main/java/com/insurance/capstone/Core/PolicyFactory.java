package com.insurance.capstone.Core;

import java.time.LocalDate;

import com.insurance.capstone.Auto.AutoInsurance;
import com.insurance.capstone.Auto.AutoQuote;
import com.insurance.capstone.Auto.Automobile;
import com.insurance.capstone.Home.HomeInsurance;
import com.insurance.capstone.Home.HomeQuote;
import com.insurance.capstone.User.Customer;

public class PolicyFactory {

    public static Policy createPolicyFromQuote(Quote quote, LocalDate startDate) {
        if (quote instanceof AutoQuote autoQuote) {
            System.out.println("Creating AutoPolicy from Quote ID: " + autoQuote.getQuoteId());
            return createAutoPolicy(autoQuote, startDate);
        } else if (quote instanceof HomeQuote homeQuote) {
            System.out.println("Creating HomePolicy from Quote ID: " + homeQuote.getQuoteId());
            return createHomePolicy(homeQuote, startDate);
        } else {
            throw new IllegalArgumentException("Unsupported quote type: " + quote.getClass().getSimpleName());
        }
    }

    private static AutoInsurance createAutoPolicy(AutoQuote quote, LocalDate startDate) {
        Automobile auto = quote.getAutomobile();
        Customer insured = quote.getInsuredPerson();
        return new AutoInsurance(
                "AUTO-" + quote.getQuoteId(),
                insured,
                startDate,
                quote.getBasePremium(),
                quote.getTaxRate(),
                quote.getTotalPremium(),
                quote.hasHomePolicyDiscount(),
                auto,
                startDate
        );
    }

    private static HomeInsurance createHomePolicy(HomeQuote quote, LocalDate startDate) {
        return new HomeInsurance(
                "HOME-" + quote.getQuoteId(),
                quote.getInsuredPerson(),
                startDate,
                quote.getBasePremium(),
                quote.getTaxRate(),
                quote.getTotalPremium(),
                quote.getHome(),
                quote.hasAutoPolicyDiscount(),
                startDate
        );
    }
}
