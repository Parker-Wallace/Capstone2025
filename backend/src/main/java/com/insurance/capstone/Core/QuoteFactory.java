package com.insurance.capstone.Core;

import com.insurance.capstone.Auto.AutoQuote;
import com.insurance.capstone.Auto.Automobile;
import com.insurance.capstone.Home.HomeQuote;
import com.insurance.capstone.Home.Home;
import com.insurance.capstone.User.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class QuoteFactory {

    private final double taxRate = 1.15;
    private final double homeBasePremium = 500.0;
    private final double autoBasePremium = 750.0;

    public AutoQuote createAutoQuote(
            String quoteId,
            Customer insuredPerson,
            LocalDate startDate,
            boolean hasHomePolicyDiscount,
            Automobile automobile
    ) {
        int driverAge = insuredPerson.getAge();

        double homeDiscountFactor = hasHomePolicyDiscount ? 0.9 : 1.0;
        double accidentFactor = (automobile.getNumberofAccidents() > 2) ? 2.5 :
                (automobile.getNumberofAccidents() == 1) ? 1.25 : 1.0;
        double ageFactor = (driverAge < 25) ? 2.0 : 1.0;
        int vehicleAge = LocalDate.now().getYear() - automobile.getVehicleYear();
        double vehicleFactor = (vehicleAge > 10) ? 2.0 :
                (vehicleAge > 5) ? 1.5 : 1.0;

        double totalPremium = autoBasePremium *
                ageFactor *
                accidentFactor *
                vehicleFactor *
                homeDiscountFactor *
                taxRate;

        totalPremium = Math.round(totalPremium * 100.0) / 100.0;

        return new AutoQuote(
                quoteId,
                insuredPerson,
                startDate,
                autoBasePremium,
                taxRate,
                totalPremium,
                hasHomePolicyDiscount,
                automobile
        );
    }

    public HomeQuote createHomeQuote(
            String quoteId,
            Customer insuredPerson,
            LocalDate startDate,
            Home home,
            boolean hasAutoPolicyDiscount
    ) {
        double homeValueFactor = (home.getHomeValue() > 250000) ? (home.getHomeValue() - 250000) * 0.002 : 0;
        int homeAge = LocalDate.now().getYear() - home.getYearBuilt();
        double homeAgeFactor = (homeAge > 50) ? 1.5 : (homeAge > 25) ? 1.25 : 1.0;
        double heatingFactor = home.getHeatingType() == Home.HEATINGTYPE.oil ? 2.0 :
                home.getHeatingType() == Home.HEATINGTYPE.gas ? 1.0 : 1.25;
        double locationFactor = home.getLocationType() == Home.LOCATIONTYPE.rural ? 1.15 : 1.0;
        double discountFactor = hasAutoPolicyDiscount ? 0.9 : 1.0;

        double totalPremium = (homeBasePremium + homeValueFactor) * homeAgeFactor *
                heatingFactor * locationFactor * discountFactor * taxRate;

        totalPremium = Math.round(totalPremium * 100.0) / 100.0;

        return new HomeQuote(
                quoteId,
                insuredPerson,
                startDate,
                homeBasePremium,
                taxRate,
                totalPremium,
                home,
                hasAutoPolicyDiscount
        );
    }
}
