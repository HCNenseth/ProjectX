package main.model.insurance;

import main.model.Status;
import main.model.claim.Claim;
import main.model.person.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * InsuranceBuilder.java
 * <p>
 * InsuranceBuilder abstract class.
 * All insurances builders should extend from this class.
 * @param <T>  the type parameter
 * @param <E>  the type parameter
 */
public abstract class InsuranceBuilder<T, E>
{
    private Person customer;
    private double amount = 0;
    private double premium = 0;
    private double deductible;
    private LocalDate date = LocalDate.now();
    private String desc = "";
    private Status status = Status.ACTIVE;

    /**
     * Customer t.
     *
     * @param customer the customer
     * @return the t
     */
    public T customer(Person customer)
    {
        this.customer = customer;
        return (T) this;
    }

    /**
     * Amount t.
     *
     * @param amount the amount
     * @return the t
     */
    public T amount(double amount)
    {
        this.amount = amount;
        return (T) this;
    }

    /**
     * Premium t.
     *
     * @param premium the premium
     * @return the t
     */
    public T premium(double premium)
    {
        this.premium = premium;
        return (T) this;
    }

    /**
     * Deductible t.
     *
     * @param deductible the deductible
     * @return the t
     */
    public T deductible(double deductible)
    {
        this.deductible = deductible;
        return (T) this;
    }

    /**
     * Date t.
     *
     * @param date the date
     * @return the t
     */
    public T date(LocalDate date)
    {
        this.date = date;
        return (T) this;
    }

    /**
     * Desc t.
     *
     * @param desc the desc
     * @return the t
     */
    public T desc(String desc)
    {
        this.desc = desc;
        return (T) this;
    }

    /**
     * Status t.
     *
     * @param status the status
     * @return the t
     */
    public T status(Status status)
    {
        this.status = status;
        return (T) this;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Person getCustomer()
    {
        return customer;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Gets premium.
     *
     * @return the premium
     */
    public double getPremium()
    {
        return premium;
    }

    /**
     * Gets deductible.
     *
     * @return the deductible
     */
    public double getDeductible()
    {
        return deductible;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate()
    {
        return date;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Build e.
     *
     * @return the e
     */
    public abstract E build();

    /**
     * Gets claims list.
     *
     * @return the claims list
     */
    public abstract List<? extends Claim> getClaimsList();
}
