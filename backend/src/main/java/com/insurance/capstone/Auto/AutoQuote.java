package com.insurance.capstone.Auto;

import com.insurance.capstone.Core.Quote;
import com.insurance.capstone.User.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "auto_quotes")
public class AutoQuote extends Quote {

    private boolean hasHomePolicyDiscount;

    @Embedded
    private com.insurance.capstone.Auto.Automobile automobile;

    protected AutoQuote() {
    }

    public AutoQuote(String quoteId,
                     Customer insuredPerson,
                     LocalDate startDate,
                     double basePremium,
                     double taxRate,
                     double totalPremium,
                     boolean hasHomePolicyDiscount,
                     com.insurance.capstone.Auto.Automobile automobile) {

        super(quoteId, insuredPerson, startDate, basePremium, taxRate, totalPremium);
        this.automobile = automobile;
        this.hasHomePolicyDiscount = hasHomePolicyDiscount;
    }

    public boolean hasHomePolicyDiscount() {
        return hasHomePolicyDiscount;
    }

    public com.insurance.capstone.Auto.Automobile getAutomobile() {
        return automobile;
    }

    // Convenience methods
    public String getVehicleMake() {
        return automobile.getVehicleMake();
    }

    public String getVehicleModel() {
        return automobile.getVehicleModel();
    }

    public int getVehicleYear() {
        return automobile.getVehicleYear();
    }

    public int getAccidents() {
        return automobile.getNumberofAccidents();
    }
}
