package com.insurance.capstone.Home;

import com.insurance.capstone.Core.Policy;
import com.insurance.capstone.User.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "home_policies")
public class HomeInsurance extends Policy {

    @Embedded
    private Home insuredHome;

    private boolean hasAutoPolicyDiscount;

    protected HomeInsurance() {
        // Required by JPA
    }

    public HomeInsurance(String policyId,
                         Customer insuredPerson,
                         LocalDate startDate,
                         double basePremium,
                         double taxRate,
                         double totalPremium,
                         Home insuredHome,
                         boolean hasAutoPolicyDiscount,
                         LocalDate paymentDate) {

        super(policyId, insuredPerson, startDate, basePremium, taxRate, totalPremium, paymentDate);
        this.insuredHome = insuredHome;
        this.hasAutoPolicyDiscount = hasAutoPolicyDiscount;
    }

    public Home getHome() {
        return insuredHome;
    }

    public boolean hasAutoPolicyDiscount() {
        return hasAutoPolicyDiscount;
    }

    public int getYearBuilt() {
        return insuredHome.getYearBuilt();
    }

    public String getDwellingType() {
        return insuredHome.getDwellingType().name();
    }

    public String getHeatingType() {
        return insuredHome.getHeatingType().name();
    }

    public String getLocationType() {
        return insuredHome.getLocationType().name();
    }

    public double getHomeValue() {
        return insuredHome.getHomeValue();
    }

    public double getLiabilityLimit() {
        return insuredHome.getLiabilityLimit();
    }

    @Override
    public HomeInsurance withPaymentDate(LocalDate date) {
        return new HomeInsurance(
                getPolicyId(),
                getInsuredPerson(),
                getStartDate(),
                getBasePremium(),
                getTaxRate(),
                getTotalPremium(),
                insuredHome,
                hasAutoPolicyDiscount,
                date
        );
    }
}
