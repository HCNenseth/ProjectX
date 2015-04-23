package main.controller;

/**
 * Created by alex on 4/23/15.
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
