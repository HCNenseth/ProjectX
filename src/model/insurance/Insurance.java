package model.insurance;

import model.FullTextSearch;
import model.Person;
import model.Status;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public abstract class Insurance<T> implements Serializable, Type, FullTextSearch
{
    private Person customer;
    private double premium;
    private double amount;
    private double deductable;
    private Calendar date;
    private String desc;

    private Status status;

    public Insurance(InsuranceBuilder ib)
    {
        this.customer = ib.getCustomer();
        this.premium = ib.getPremium();
        this.amount = ib.getAmount();
        this.date = ib.getDate();
        this.desc = ib.getDesc();
        this.status = ib.getStatus();

        customer.setInsurance(this);
    }

    public String toString()
    {
        return "Customer:\t" + customer + "\nPremium:\t" + premium + "\nAmout:\t"
                + amount + "\nDeductable:\t" + deductable + "\nDescription:\t"
                + desc + "\nDate:\t" + date;
    }

    public Person getCustomer() { return customer; }

    public boolean query(String value)
    {
        // TODO implement test for all members!
        return (desc != null && desc.contains(value));
    }
}
