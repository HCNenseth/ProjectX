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

/**
 * Main insurance class.
 */
public abstract class Insurance<C extends Claim> implements
        Serializable, Type, FullTextSearch, Model
{
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

        customer.addInsurance(this);
    }

    /* GETTERS */
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

    public LocalDate getDate()
    {
        return date;
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

    @Override
    public boolean query(String value)
    {
        // TODO implement test for all members!
        return (desc != null && desc.contains(value));
    }

    public static void saveNew(Insurance insurance)
    {
        ((List<Insurance>) Storage.getInstance().get(Config.INSURANCES)).add(insurance);
    }

    @Override
    public Status getStatus()
    {
        return status;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n" +
                "%s:\t%s\n",
                Loc.c("customer"), customer,
                Loc.c("premium"), premium,
                Loc.c("amount"), amount,
                Loc.c("deductible"), deductible,
                Loc.c("description"), desc,
                Loc.c("date"), date

        );
    }

    @Override
    public ModelType getModelType() { return ModelType.INSURANCE; }
}
