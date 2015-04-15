package model.insurance.vehicle;

import model.Person;


import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Car extends Vehicle
{

    private int registrationYear;
    private int mileage;
    private int bonus;
    private enum type{A,B,C}

    public Car(Person customer, double premium, double amount, Calendar date, String desc)
    {
        super(customer, premium, amount, date, desc);
    }

}
