package com.insurance.capstone.Auto;

import com.insurance.capstone.Core.Policy;
import com.insurance.capstone.User.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "auto_policies")
public class AutoInsurance extends Policy {

    private boolean hasHomePolicyDiscount;

    @Embedded
    private Automobile automobile;

    protected AutoInsurance() {
        // Required by JPA
    }

    public AutoInsurance(String policyId,
                         Customer insuredPerson,
                         LocalDate startDate,
                         double basePremium,
                         double taxRate,
                         double totalPremium,
                         boolean hasHomePolicyDiscount,
                         Automobile automobile,
                         LocalDate paymentDate) {

        super(policyId, insuredPerson, startDate, basePremium, taxRate, totalPremium, paymentDate);
        this.automobile = automobile;
        this.hasHomePolicyDiscount = hasHomePolicyDiscount;
    }

    public boolean hasHomePolicyDiscount() {
        return hasHomePolicyDiscount;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public int getDriverAge() {
        return getInsuredPerson().getAge();
    }

    // Convenience methods — just like AutoQuote
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

    @Override
    public AutoInsurance withPaymentDate(LocalDate date) {
        return new AutoInsurance(
                getPolicyId(),
                getInsuredPerson(),
                getStartDate(),
                getBasePremium(),
                getTaxRate(),
                getTotalPremium(),
                hasHomePolicyDiscount,
                automobile,
                date
        );
    }
}
