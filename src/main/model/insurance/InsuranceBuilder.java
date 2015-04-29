package main.model.insurance;

import main.model.person.Person;
import main.model.Status;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * InsuranceBuilder abstract class.
 * All insurances builders should extend from this class.
 */
public abstract class InsuranceBuilder<T, E>
{
    private Person customer;
    private Person owner = customer;
    private double amount = 0;
    private double premium = 0;
    private double deductible;
    private LocalDate date = LocalDate.now();
    private String desc = "";
    private Status status = Status.ACTIVE;

    /* SETTERS */
    public T customer(Person customer)
    {
        this.customer = customer;
        return (T)this;
    }

    public T owner(Person owner)
    {
        this.owner = owner;
        return (T)this;
    }

    public T amount(int amount)
    {
        this.amount = amount;
        return (T)this;
    }

    public T premium(int premium)
    {
        this.premium = premium;
        return (T)this;
    }

    public T deductible(int deductible)
    {
        this.deductible = deductible;
        return (T)this;
    }

    public T date(LocalDate date)
    {
        this.date = date;
        return (T)this;
    }

    public T desc(String desc)
    {
        this.desc = desc;
        return (T)this;
    }

    public T status(Status status)
    {
        this.status = status;
        return (T)this;
    }

    /* GETTERS */
    public Person getCustomer() { return customer; }

    public Person getOwner(){ return owner; }

    public double getAmount() { return amount; }

    public double getPremium() { return premium; }

    public double getDeductible() { return deductible; }

    public LocalDate getDate() { return date; }

    public String getDesc() { return desc; }

    public Status getStatus() { return status; }

    /* ABSTRACT */
    public abstract E build();
}
