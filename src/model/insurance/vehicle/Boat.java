package model.insurance.vehicle;

import model.Person;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Boat extends Vehicle
{
    private int registrationYear;
    private short length;
    private short horsePower;
    private enum propultion {A, B, C}
    private enum type{A,B,C}

    public Boat(Person customer, double premium, double amount, Calendar date, String desc)
    {
        super(customer, premium, amount, date, desc);
    }

}

