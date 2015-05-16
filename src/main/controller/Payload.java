package main.controller;

/**
 * Payload.java
 */
public class Payload
{
    private String string;
    private Enum e;

    /**
     * Instantiates a new Payload.
     */
    public Payload()
    {
        this("");
    }

    /**
     * Instantiates a new Payload.
     *
     * @param e the e
     */
    public Payload(Enum e)
    {
        this.e = e;
    }

    /**
     * Instantiates a new Payload.
     *
     * @param string the string
     */
    public Payload(String string)
    {
        this.string = string;
    }

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString()
    {
        return string;
    }

    /**
     * Gets enum value.
     *
     * @return the enum value
     */
    public Enum getEnumValue()
    {
        return e;
    }
}
