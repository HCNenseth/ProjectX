package main.model.insurance;

import main.localization.Loc;

/**
 * Created by alex on 4/17/15.
 */
public enum ConcreteType
{
    HOUSE(Loc.get("house_insurance")),
    VACATION_HOUSE(Loc.get("vacation_house_insurance")),
    CONTENTS(Loc.get("contents_insurance")),
    TRAVEL(Loc.get("travel_insurance")),
    BOAT(Loc.get("boat_insurance")),
    CAR(Loc.get("car_insurance"));

    String value;

    ConcreteType(String value) { this.value = value; }

    public String getValue() { return value; }

    @Override
    public String toString() { return getValue(); }
}
