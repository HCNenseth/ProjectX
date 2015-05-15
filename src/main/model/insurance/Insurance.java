package main.model.insurance;

import main.config.Config;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.Status;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.person.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Insurance.java
 * Main insurance class.
 */
public abstract class Insurance<C extends Claim> implements
        Serializable, Type, FullTextSearch, Model
{
    private static int counter = Config.INSURANCE_COUNTER_START;
    private int id;

    private Person customer;
    private double premium;
    private double amount;
    private double deductible;
    private LocalDate date;
    private LocalDate lastEdited;
    private String desc;
    private Status status;

    private List<C> claims;

    /**
     * Insurance constructor.
     * @param ib
     */
    public Insurance(InsuranceBuilder ib)
    {
        this.customer = ib.getCustomer();
        this.premium = ib.getPremium();
        this.amount = ib.getAmount();
        this.deductible = ib.getDeductible();
        this.date = ib.getDate();
        this.desc = ib.getDesc();
        this.status = ib.getStatus();
        this.claims = ib.getClaimsList();

        id = counter++;
        lastEdited = null;

        customer.addInsurance(this);
    }

    /* GETTERS */
    public String getId()
    {
        return Integer.toString(id);
    }

    public double getPremium()
    {
        return premium;
    }

    public double getAmount()
    {
        return amount;
    }

    public double getDeductible()
    {
        return deductible;
    }

    public String getDesc()
    {
        return desc;
    }

    public Person getCustomer()
    {
        return customer;
    }

    public List<C> getClaims()
    {
        return claims;
    }

    public LocalDate getLastEdited()
    {
        return lastEdited;
    }

    /* SETTERS */
    public void setDesc(String desc)
    {
        this.desc = desc;
        this.lastEdited = LocalDate.now();
    }

    public void setPremium(double val)
    {
        premium = val;
        this.lastEdited = LocalDate.now();
    }

    public void setAmount(double val)
    {
        amount = val;
        this.lastEdited = LocalDate.now();
    }

    public void setDeductible(double val)
    {
        deductible = val;
        this.lastEdited = LocalDate.now();
    }

    public void setStatus(Status status)
    {
        this.status = status;
        this.lastEdited = LocalDate.now();
    }

    public void addClaim(C claim)
    {
        claims.add(claim);
        this.lastEdited = LocalDate.now();
    }

    /* STATIC */
    public static List<Insurance> getInsurances()
    {
        return (List<Insurance>)Storage.getInstance().get(Config.INSURANCES);
    }

    public static void saveNew(Insurance insurance)
    {
        Insurance.getInsurances().add(insurance);
    }

    public static void setCounter(int val)
    {
        counter += val;
    }

    /* OVERRIDES */
    @Override
    public boolean query(String value)
    {
        return (desc != null && desc.contains(value))
                || (getId().contains(value));
    }

    @Override
    public Status getStatus()
    {
        return status;
    }

    @Override
    public ModelType getModelType()
    {
        return ModelType.INSURANCE;
    }

    @Override
    public LocalDate getDate()
    {
        return date;
    }

    @Override
    public LocalDate getEdited()
    {
        return lastEdited;
    }
}
