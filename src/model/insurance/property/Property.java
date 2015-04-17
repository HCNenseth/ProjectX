package model.insurance.property;

import model.Person;
import model.insurance.Insurance;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Property extends Insurance implements Serializable
{
    public Property(Person customer,
                    double premium,
                    double amount,
                    Calendar date,
                    String desc)
    {
        super(customer, premium, amount, date, desc);
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value); // only push upstream
    }
}
