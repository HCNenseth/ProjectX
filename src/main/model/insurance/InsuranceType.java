package main.model.insurance;

import main.localization.Loc;

/**
 * InsuranceType.java
 */
public enum InsuranceType
{
    HOUSE(Loc.c("house")),
    VACATION_HOUSE(Loc.c("vacation_house")),
    TRAVEL(Loc.c("travel")),
    BOAT(Loc.c("boat")),
    CAR(Loc.c("car"));

    String value;

    InsuranceType(String value) { this.value = value; }

    public static InsuranceType getType(String value)
    {
        for (InsuranceType type : values())
            if (type.getValue().equals(value))
                return type;
        return null;
    }

    public String getValue() { return value; }

    @Override
    public String toString() { return getValue(); }
}
