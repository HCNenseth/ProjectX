package model.insurance;

import java.util.Calendar;

/**
 * Created by alex on 4/16/15.
 */
public abstract class InsuranceBuilder<T, E>
{
    private double amount = 0;
    private double premium = 0;
    private Calendar date = null;
    private String desc = "";

    public T amount(double amount)
    {
        this.amount = amount; return (T)this;
    }

    public T premium(double premium)
    {
        this.premium = premium; return (T)this;
    }

    public T date(Calendar date)
    {
        this.date = date; return (T)this;
    }

    public T desc(String desc)
    {
        this.desc = desc; return (T)this;
    }

    public double getAmount()
    {
        return amount;
    }

    public double getPremium()
    {
        return premium;
    }

    public Calendar getDate()
    {
        return date;
    }

    public String getDesc()
    {
        return desc;
    }

    public abstract E build();
}
