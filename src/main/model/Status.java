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
    ACTIVE(Loc.get("active")),
    INACTIVE(Loc.get("inactive")),
    PENDING(Loc.get("pending")),
    INCOMPLETE(Loc.get("incomplete"));

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
