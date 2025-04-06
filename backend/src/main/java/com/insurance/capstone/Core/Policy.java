package com.insurance.capstone.Core;

import com.insurance.capstone.User.Customer;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "policies")
public abstract class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyId;

    @ManyToOne
    @JsonBackReference
    private Customer insuredPerson;

    private LocalDate startDate;
    private LocalDate endDate;
    private double basePremium;
    private double taxRate;
    private double totalPremium;
    private LocalDate paymentDate;

    protected Policy() {
        // Required by JPA
    }

    public Policy(String policyId,
                  Customer insuredPerson,
                  LocalDate startDate,
                  double basePremium,
                  double taxRate,
                  double totalPremium,
                  LocalDate paymentDate) {

        this.policyId = policyId;
        this.insuredPerson = insuredPerson;
        this.startDate = startDate;
        this.endDate = startDate.plusYears(1);
        this.basePremium = basePremium;
        this.taxRate = taxRate;
        this.totalPremium = totalPremium;
        this.paymentDate = paymentDate;

        insuredPerson.addPolicy(this);
    }

    public Long getId() {
        return id;
    }

    public String getPolicyId() {
        return policyId;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTotalPremium() {
        return totalPremium;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public abstract Policy withPaymentDate(LocalDate date);
}
