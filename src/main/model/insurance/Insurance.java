package main.model.insurance;

import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.claim.Claim;
import main.model.person.Person;
import main.model.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Main insurance class.
 */
public abstract class Insurance implements
        Serializable, Type, FullTextSearch, Model
{
    private Person customer;
    private double premium;
    private double amount;
    private double deductible;
    private LocalDate date;
    private String desc;
    private Status status;

    private List<Claim> claims;

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

        customer.addInsurance(this);

        claims = new LinkedList<>();
    }

    public void addClaim(Claim claim)
    {
        claims.add(claim);
    }

    public double getPremium()
    {
        return premium;
    }

    public double getAmount() {
        return amount;
    }

    public double getDeductible() {
        return deductible;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public void setPremium(int val)
    {
        premium = val;
    }

    public void setAmount(int val)
    {
        amount = val;
    }

    public void setStatus(Status s)
    {
        status = s;
    }

    public Person getCustomer() { return customer; }

    public List<Claim> getClaims() { return claims; }

    public boolean query(String value)
    {
        // TODO implement test for all members!
        return (desc != null && desc.contains(value));
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
                Loc.get("customer"), customer,
                Loc.get("premium"), premium,
                Loc.get("amount"), amount,
                Loc.get("deductible"), deductible,
                Loc.get("description"), desc,
                Loc.get("date"), date

        );
    }

    @Override
    public ModelType getModelType() { return ModelType.INSURANCE; }
}
