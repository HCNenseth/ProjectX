package main.model;

import main.localization.Loc;

/**
 * Status.java
 */
public enum Status
{
    ACTIVE(Loc.c("active")),
    INACTIVE(Loc.c("inactive")),
    PENDING(Loc.c("pending")),
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
