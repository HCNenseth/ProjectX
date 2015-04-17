package model.insurance.travel;

import model.Person;
import model.insurance.ConcreteType;
import model.insurance.Insurance;
import model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Travel extends Insurance implements Serializable
{

    public static class Builder extends InsuranceBuilder<Builder, Travel>
    {
        private Person customer;

        public Builder(Person customer)
        {
            this.customer = customer;
        }

        public Travel build()
        {
            return new Travel(this);
        }
    }

    private Travel(Builder builder)
    {
        super(builder.customer,
              builder.getPremium(),
              builder.getAmount(),
              builder.getDate(),
              builder.getDesc());
    }

    public ConcreteType identify()
    {
        return ConcreteType.TRAVEL;
    }

    public boolean query(String value)
    {
        return super.query(value);
    }

    // TODO override equals and hashcode
}
