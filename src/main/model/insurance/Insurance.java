package main.model.insurance;

import main.config.Config;
import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.person.Person;
import main.model.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main insurance class.
 */
public abstract class Insurance<C extends Claim> implements
        Serializable, Type, FullTextSearch, Model
{
    public static final int insuranceId = 1;

    private int id;
    private static int insuranceCount = insuranceId;

    private Person customer;
    private double premium;
    private double amount;
    private double deductible;
    private LocalDate date;
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
        id = insuranceCount++;

        customer.addInsurance(this);
    }

    /* GETTERS */
    public int getId()
    {
        return id;
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

    /* SETTERS */
    public void setInsuranceCount(int val)
    {
        insuranceCount = val;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public void setPremium(double val)
    {
        premium = val;
    }

    public void setAmount(double val)
    {
        amount = val;
    }

    public void setDeductible(double val)
    {
        deductible = val;
    }

    public void setStatus(Status s)
    {
        status = s;
    }

    public void addClaim(C claim)
    {
        claims.add(claim);
    }

    /* STATIC */
    public static void saveNew(Insurance insurance)
    {
        ((List<Insurance>) Storage.getInstance().get(Config.INSURANCES)).add(insurance);
    }

    /* OVERRIDES */
    @Override
    public boolean query(String value)
    {
        return (desc != null && desc.contains(value));
    }

    @Override
    public Status getStatus()
    {
        return status;
    }

    @Override
    public ModelType getModelType() { return ModelType.INSURANCE; }

    @Override
    public LocalDate getDate() { return date; }
}
