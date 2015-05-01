package main.model.insurance;

import main.localization.Loc;

/**
 * Created by alex on 4/17/15.
 */
public enum ConcreteType
{
    HOUSE(Loc.c("house_insurance")),
    VACATION_HOUSE(Loc.c("vacation_house_insurance")),
    CONTENTS(Loc.c("contents_insurance")),
    TRAVEL(Loc.c("travel_insurance")),
    BOAT(Loc.c("boat_insurance")),
    CAR(Loc.c("car_insurance"));

    String value;

    ConcreteType(String value) { this.value = value; }

    public String getValue() { return value; }

    @Override
    public String toString() { return getValue(); }
}
