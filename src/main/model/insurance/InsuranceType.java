package main.model.insurance;

import main.localization.Loc;

/**
 * InsuranceType.java
 */
public enum InsuranceType
{
    /**
     * The HOUSE.
     */
    HOUSE(Loc.c("house")),
    /**
     * The VACATION_HOUSE.
     */
    VACATION_HOUSE(Loc.c("vacation_house")),
    /**
     * The TRAVEL.
     */
    TRAVEL(Loc.c("travel")),
    /**
     * The BOAT.
     */
    BOAT(Loc.c("boat")),
    /**
     * The CAR.
     */
    CAR(Loc.c("car"));

    /**
     * The Value.
     */
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
