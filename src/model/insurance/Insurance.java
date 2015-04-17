package model.insurance;

import localization.Loc;
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
    private double deductible;
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

    public Person getCustomer() { return customer; }

    public boolean query(String value)
    {
        // TODO implement test for all members!
        return (desc != null && desc.contains(value));
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n",
                Loc.get("customer"), customer,
                Loc.get("premium"), premium,
                Loc.get("amount"), amount,
                Loc.get("deductible"), deductible,
                Loc.get("description"), desc,
                Loc.get("date"), date

        );
    }

}
