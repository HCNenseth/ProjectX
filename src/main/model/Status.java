package main.model;

import main.localization.Loc;

/**
 * Status.java
 */
public enum Status
{
    /**
     * Generic statuses for the three main model types {person, insurance, claim}
     */
    ACTIVE(Loc.c("active")),
    /**
     * The INACTIVE.
     */
    INACTIVE(Loc.c("inactive")),
    /**
     * The PENDING.
     */
    PENDING(Loc.c("pending")),
    /**
     * The INCOMPLETE.
     */
    INCOMPLETE(Loc.c("incomplete"));

    private String value;

    /**
     * Instantiates a new Status.
     *
     * @param value the value
     */
    Status(String value)
    {
        this.value = value;
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
        return value;
    }
}
