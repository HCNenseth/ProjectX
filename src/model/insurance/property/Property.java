package model.insurance.property;

import model.Person;
import model.insurance.Insurance;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Property extends Insurance
{
    public Property(Person customer,
                    double premium,
                    double amount,
                    Calendar date,
                    String desc)
    {
        super(customer, premium, amount, date, desc);
    }
}
