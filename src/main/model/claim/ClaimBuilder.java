package main.model.claim;

import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.time.LocalDate;

/**
 * Created by HansPetter on 06.05.2015.
 */
public abstract class ClaimBuilder<T, E>  {

    private int reference;
    private Person customer;
    private Insurance insurance;

    private LocalDate accidentDate = null;
    private LocalDate claimDate = LocalDate.now();

    private String desc = null;
    private String contacts = null;
    private double amount = 0;

    private Status status = Status.INCOMPLETE;

    /* Setters */
    public T customer(Person customer)
    {
        this.customer = customer;
        return (T) this;
    }

    public T insurance(Insurance insurance)
    {
        this.insurance = insurance;
        return (T) this;
    }

    public T amount(double amount)
    {
        this.amount = amount;
        return (T) this;
    }

    public T contacts(String contacts)
    {
        this.contacts = contacts;
        return (T)this;
    }

    public T desc(String desc)
    {
        this.desc = desc;
        return (T) this;
    }

    private T accidentDate(LocalDate accidentDate)
    {
        this.accidentDate = accidentDate;
        return (T) this;
    }

    public T status(Status status)
    {
        this.status = status;
        return (T)this;
    }

    /* Getters */

    public Person getCustomer()
    {
        return customer;
    }

    public Insurance getInsurance()
    {
        return insurance;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getContacts()
    {
        return contacts;
    }

    public String getDesc()
    {
        return desc;
    }

    public LocalDate getAccidentDate()
    {
        return accidentDate;
    }

    public LocalDate getClaimDate()
    {
        return claimDate;
    }

    public Status getStatus()
    {
        return status;
    }

    public Claim.PaymentStatus getPaymentStatus()
    {
        return getPaymentStatus();
    }

    /* Abstract */
    public abstract E build();
}
