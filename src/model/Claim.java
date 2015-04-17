package model;

import localization.Loc;
import model.insurance.Insurance;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Hans Petter on 16.04.2015.
 */
public class Claim implements Serializable, FullTextSearch
{
    private String desc;
    private String contacts;
    private double amount;
    private Calendar date;
    private Person customer;
    private Insurance insurance;
    private Type type;
    private PaymentStatus paymentStatus;
    private Status status;

    public enum PaymentStatus {
        A(Loc.get("payment_status_a")),
        B(Loc.get("payment_status_b")),
        C(Loc.get("payment_status_c"));

        String value;
        PaymentStatus(String value){ this.value = value; }

        public String getValue() { return value; }
    }

    public enum Type {
        A(Loc.get("claim_type_a")),
        B(Loc.get("claim_type_b")),
        C(Loc.get("claim_type_a"));

        String value;
        Type(String value){ this.value = value; }

        public String getValue() {  return value;   }
    }

    public static class Builder
    {
        // required
        private Person customer;
        private Insurance insurance;

        // optional
        private String description = "";
        private String contacts = "";
        private double amount = 0;
        private Calendar date;
        private Type type = Type.A;
        private Status status = Status.ACTIVE;

        public Builder(Person customer, Insurance insurance){
            this.customer = customer;
            this.insurance = insurance;
        }

        public Builder status(Status val)
        {
            status = val;
            return this;
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

        public Builder type(Type val)
        {
            type = val;
            return this;
        }
    }

    public Claim(Builder builder)
    {
        customer = builder.customer;
        insurance = builder.insurance;
        desc = builder.description;
        contacts = builder.contacts;
        date = builder.date;
        type = builder.type;
        status = builder.status;
    }

    public Person getCustomer()
    {
        return customer;
    }

    public Insurance getInsurance()
    {
        return insurance;
    }

    public Calendar getDate() { return date; }

    public boolean query(String value)
    {
        return desc.contains(value)
                || contacts.contains(value)
                || type.getValue().contains(value);
    }

    public float payout()
    {
        return 0;
        //return amount - insurance.getDeductable();
    }

    // TODO override equals and hashcode
}
