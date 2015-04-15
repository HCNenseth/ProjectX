package model.insurance.property;

import model.Person;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class House extends Property
{
    private String streetAddress;
    private short postalCode;
    private String city;
    private int year;
    private enum type {A, B, C}
    private enum material {A, B, C}
    private enum standard {A, B, C}
    private int squareMeter;

    public House(Person customer, double premium, double amount, Calendar date, String desc)
    {
        super(customer, premium, amount, date, desc);
    }
}
