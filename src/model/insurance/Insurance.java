package model.insurance;

import model.Person;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public abstract class Insurance implements Serializable
{
    private Person customer;
    private double premium;
    private double amount;
    private double deductable;
    private Calendar date;
    private String desc;

    public Insurance(Person customer, double premium, double amount, Calendar date, String desc)
    {
        this.customer = customer;
        this.premium = premium;
        this.amount = amount;
        this.date = date;
        this.desc = desc;

        customer.setInsurance(this);
    }

    public String toString()
    {
        return "Customer:\t" + customer + "\nPremium:\t" + premium + "\nAmout:\t"
                + amount + "\nDeductable:\t" + deductable + "\nDescription:\t"
                + desc + "\nDate:\t" + date;
    }

    public Person getCustomer() { return customer; }
}
