package main.model;

import main.localization.Loc;

/**
 * Created by alex on 4/17/15.
 */
public enum Status
{
    /**
     * Generic statuses for the three main model types {person, insurance, claim}
     */
    ACTIVE(Loc.c("active")),
    INACTIVE(Loc.c("inactive")),
    PENDING(Loc.c("pending")),
    INCOMPLETE(Loc.c("incomplete"));

    private String value;

    Status(String value)
    {
        this.value = value;
    }

    public String getValue() { return value; }

    @Override
    public String toString()
    {
        return value;
    }

}
