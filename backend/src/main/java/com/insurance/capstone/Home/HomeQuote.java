package com.insurance.capstone.Home;

import com.insurance.capstone.Core.Quote;
import com.insurance.capstone.User.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "home_quotes")
public class HomeQuote extends Quote {

    @Embedded
    private Home home;

    private boolean hasAutoPolicyDiscount;

    protected HomeQuote() {
    }

    public HomeQuote(String quoteId, Customer insuredPerson, LocalDate startDate,
                     double basePremium, double taxRate, double totalPremium,
                     Home home, boolean hasAutoPolicyDiscount) {

        super(quoteId, insuredPerson, startDate, basePremium, taxRate, totalPremium);
        this.home = home;
        this.hasAutoPolicyDiscount = hasAutoPolicyDiscount;
    }

    public Home getHome() {
        return home;
    }

    public boolean hasAutoPolicyDiscount() {
        return hasAutoPolicyDiscount;
    }

    public int getYearBuilt() {
        return home.getYearBuilt();
    }

    public Home.DWELLINGTYPE getDwellingType() {
        return home.getDwellingType();
    }

    public Home.HEATINGTYPE getHeatingType() {
        return home.getHeatingType();
    }

    public Home.LOCATIONTYPE getLocationType() {
        return home.getLocationType();
    }

    public double getHomeValue() {
        return home.getHomeValue();
    }

    public double getLiabilityLimit() {
        return home.getLiabilityLimit();
    }
}
