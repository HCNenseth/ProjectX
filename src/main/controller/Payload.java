package main.controller;

/**
 * Payload.java
 */
public class Payload
{
    private String string;
    private Enum e;

    public Payload() { this(""); }

    public Payload(Enum e)
    {
        this.e = e;
    }

    public Payload(String string)
    {
        this.string = string;
    }

    public String getString()
    {
        return string;
    }

    public Enum getEnumValue()
    {
        return e;
    }
}
