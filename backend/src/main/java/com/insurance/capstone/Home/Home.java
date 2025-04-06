package com.insurance.capstone.Home;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Represents a com.insurance.capstone.Home for use in the TaylorInsurance quoting system. contains all
 * the necessary methods and constructors
 *
 * @author Parker Wallace
 */
@Embeddable
public class Home {

    private int yearBuilt;
    private double homeValue;
    private double liabilityLimit;

    @Enumerated(EnumType.STRING)
    private DWELLINGTYPE dwellingType;

    @Enumerated(EnumType.STRING)
    private HEATINGTYPE heatingType;

    @Enumerated(EnumType.STRING)
    private LOCATIONTYPE locationType;

    public Home() {
    }

    /**
     * Constructor for this com.insurance.capstone.Home class
     *
     * @param yearBuilt      the year which this home was built
     * @param homeValue      the value of this home in canadian dollars
     * @param liabilityLimit the liability limit of this com.insurance.capstone.Home
     * @param dwellingType   the dwelling type of this com.insurance.capstone.Home
     * @param heatingType    the heating type of this com.insurance.capstone.Home
     * @param locationType   the location type for this com.insurance.capstone.Home
     */
    public Home(int yearBuilt, double homeValue, double liabilityLimit,
                String dwellingType, String heatingType, String locationType) {

        this.yearBuilt = yearBuilt;
        this.homeValue = homeValue;
        this.liabilityLimit = liabilityLimit;
        this.dwellingType = parseDwellingType(dwellingType);
        this.heatingType = parseHeatingType(heatingType);
        this.locationType = parseLocationType(locationType);
    }

    public enum DWELLINGTYPE {
        bungalow, standalone
    }

    public enum HEATINGTYPE {
        gas, oil, electric
    }

    public enum LOCATIONTYPE {
        rural, urban
    }

    /**
     * Getter method for this com.insurance.capstone.Home
     *
     * @return the dwelling type for this home
     */
    public Home.DWELLINGTYPE getDwellingType() {
        return this.dwellingType;
    }

    /**
     * Getter method for this com.insurance.capstone.Home
     *
     * @return the value for this com.insurance.capstone.Home in canadian dollars
     */
    public double getHomeValue() {
        return this.homeValue;
    }

    /**
     * Getter method for the {@see com.insurance.capstone.Home.HEATINGTYPE} for this com.insurance.capstone.Home
     *
     * @return
     */
    public Home.HEATINGTYPE getHeatingType() {
        return this.heatingType;
    }

    /**
     * Getter method for the {@see com.insurance.capstone.Home.LOCATIONTYPE} for this com.insurance.capstone.Home
     *
     * @return
     */
    public Home.LOCATIONTYPE getLocationType() {
        return this.locationType;
    }

    /**
     * Getter method for the year this com.insurance.capstone.Home was built
     *
     * @return the Year this com.insurance.capstone.Home was built
     */
    public int getYearBuilt() {
        return this.yearBuilt;
    }

    /**
     * Getter method for the liability limit of this home
     *
     * @return the liability limit for this home
     */
    public double getLiabilityLimit() {
        return this.liabilityLimit;
    }

    /**
     * Setter method for this com.insurance.capstone.Home's dwelling type
     *
     * @param dwellingType the new dwelling type for this home
     */
    public void setDwellingType(Home.DWELLINGTYPE dwellingType) {
        this.dwellingType = dwellingType;
    }

    /**
     * Setter method for this com.insurance.capstone.Home's heating type
     *
     * @param heatingType the new heating type for this com.insurance.capstone.Home
     */
    public void setHeatingType(Home.HEATINGTYPE heatingType) {
        this.heatingType = heatingType;
    }

    /**
     * Setter method for this com.insurance.capstone.Home's value
     *
     * @param value the new value for this home
     */
    public void setHomeValue(double value) {
        this.homeValue = value;
    }

    /**
     * Setter method for this com.insurance.capstone.Home's liability limit
     *
     * @param liabilityLimit the new liability limit for this com.insurance.capstone.Home
     */
    public void setLiabilityLimit(double liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    /**
     * Setter method for this com.insurance.capstone.Home's Location type
     *
     * @param locationType the new Location type for this com.insurance.capstone.Home
     */
    public void setLocation(Home.LOCATIONTYPE locationType) {
        this.locationType = locationType;
    }

    /**
     * Setter method for this com.insurance.capstone.Home's built year
     *
     * @param yearBuilt the new year this home was built
     */
    public void setYearBuilt(int yearBuilt) { this.yearBuilt = yearBuilt; }

    private DWELLINGTYPE parseDwellingType(String value) {
        try {
            return DWELLINGTYPE.valueOf(value.toLowerCase());
        } catch (IllegalArgumentException e) {
            return DWELLINGTYPE.standalone; // default
        }
    }

    private HEATINGTYPE parseHeatingType(String value) {
        try {
            return HEATINGTYPE.valueOf(value.toLowerCase());
        } catch (IllegalArgumentException e) {
            return HEATINGTYPE.electric; // default
        }
    }

    private LOCATIONTYPE parseLocationType(String value) {
        try {
            return LOCATIONTYPE.valueOf(value.toLowerCase());
        } catch (IllegalArgumentException e) {
            return LOCATIONTYPE.urban; // default
        }
    }

}
