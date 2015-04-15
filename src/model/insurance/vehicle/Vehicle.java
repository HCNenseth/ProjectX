package model.insurance.vehicle;

import model.Person;
import model.insurance.Insurance;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Vehicle extends Insurance
{
    private Person owner;
    private String regNr;
    private String model;
    private String regNrRule;

    public Vehicle(Person customer, double premium, double amount, Calendar date, String desc)
    {
        super(customer, premium, amount, date, desc);
    }
}
