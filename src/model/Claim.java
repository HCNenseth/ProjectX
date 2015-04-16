package model;

import localization.Loc;
import model.insurance.Insurance;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Claim implements Serializable
{
    private String desc;
    private String contacts;
    private double amount;
    private Calendar date;
    private Person customer;
    private Insurance insurance;
    private enum type {
        A(Loc.get("claim_type_a")),
        B(Loc.get("claim_type_b")),
        C(Loc.get("claim_type_a"));
    }

    public float payout()
    {
        return 0;
        //return amount - insurance.getDeductable();
    }

    public class Builder
    {
        private String description, contacts;
        private double amount;
        private Person customer;
        private Insurance insurance;
        private Calendar date;
        private

        public Builder(Person customer, Insurance insurance){
            this.customer = customer;
            this.insurance = insurance;
        }

        public Builder description(String val)
        {
            description = val;
            return this;
        }

        public Builder contacts(String val)
        {
            contacts = val;
            return this;
        }

        public Builder date(Calendar val)
        {
            date = val;
            return this;
        }

        public Claim build()
        {
            return new Claim(this);
        }
    }

    public Claim(Builder builder)
    {
        customer = builder.customer;
        insurance = builder.insurance;
        desc = builder.description;
        contacts = builder.contacts;
        date = builder.date;
    }
}
