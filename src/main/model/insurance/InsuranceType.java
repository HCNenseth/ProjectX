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

    /**
     * Instantiates a new Insurance type.
     *
     * @param value the value
     */
    InsuranceType(String value)
    {
        this.value = value;
    }

    /**
     * Gets type.
     *
     * @param value the value
     * @return the type
     */
    public static InsuranceType getType(String value)
    {
        for (InsuranceType type : values())
            if (type.getValue().equals(value))
                return type;
        return null;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return getValue();
    }
}
