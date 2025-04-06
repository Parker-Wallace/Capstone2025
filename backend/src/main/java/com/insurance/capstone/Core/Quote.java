package com.insurance.capstone.Core;

import com.insurance.capstone.User.Customer;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "quotes")
public abstract class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quoteId;

    @ManyToOne
    @JsonBackReference
    private Customer insuredPerson;

    private LocalDate startDate;
    private LocalDate endDate;
    private double basePremium;
    private double taxRate;
    private double totalPremium;

    protected Quote() {
        // Required by JPA
    }

    public Quote(String quoteId, Customer insuredPerson, LocalDate startDate,
                 double basePremium, double taxRate, double totalPremium) {
        this.quoteId = quoteId;
        this.insuredPerson = insuredPerson;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(30);
        this.basePremium = basePremium;
        this.taxRate = taxRate;
        this.totalPremium = totalPremium;
    }

    public Long getId() {
        return id;
    }

    public String getQuoteId() {
        return quoteId;
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
}
