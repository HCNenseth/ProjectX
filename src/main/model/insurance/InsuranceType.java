package main.model.insurance;

import main.localization.Loc;

/**
 * InsuranceType.java
 */
public enum InsuranceType
{
    HOUSE(Loc.c("house_insurance")),
    VACATION_HOUSE(Loc.c("vacation_house_insurance")),
    TRAVEL(Loc.c("travel_insurance")),
    BOAT(Loc.c("boat_insurance")),
    CAR(Loc.c("car_insurance"));

    String value;

    InsuranceType(String value) { this.value = value; }

    public String getValue() { return value; }

    @Override
    public String toString() { return getValue(); }
}
