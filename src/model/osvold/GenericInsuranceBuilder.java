package model.osvold;

import model.Person;

import java.util.Calendar;

/**
 * Created by HansPetter on 16.04.2015.
 */
abstract class GenericInsuranceBuilder
        <B extends GenericInsuranceBuilder<B>> {

    private Person customer;
    private double premium;
    private double amount;
    private double deductable;
    private Calendar date;
    private String desc;

    public B customer(Person val)
    {
        customer = val;
        return (B)this;
    }

    public B premium(double val)
    {
        premium = val;
        return (B)this;
    }

    public B amount(double val)
    {
        amount = val;
        return (B) this;
    }

    public B date(Calendar val)
    {
        date = val;
        return (B) this;
    }

    public B description(String val)
    {
        desc = val;
        return (B) this;
    }
}
