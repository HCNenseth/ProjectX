package model.insurance.vehicle;

import model.Person;
import model.insurance.Insurance;
import model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Vehicle extends Insurance implements Serializable
{
    private Person owner;
    private String regNr;
    private String regNrRule;

    public Vehicle(InsuranceBuilder ib, Person owner, String regNr)
    {
        super(ib);
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

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (regNr != null && regNr.contains(value));
    }
}
