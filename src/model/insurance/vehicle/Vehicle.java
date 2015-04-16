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
    private String regNrRule;

    public Vehicle(Person customer,
                   double premium,
                   double amount,
                   Calendar date,
                   String desc,
                   Person owner,
                   String regNr)
    {
        super(customer, premium, amount, date, desc);
        this.owner = owner;
        this.regNr = regNr;

    }

    public String toString(){
        return super.toString() + "\n\n" + "Owner:\t" + owner + "\nRegNr:\t" + regNr;
    }

    protected void setRegNrRule(String regNrRule)
    {
        this.regNrRule = regNrRule;
    }

    public String getRegNrRule()
    {
        return regNrRule;
    }
}
