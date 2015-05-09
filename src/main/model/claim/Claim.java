package main.model.claim;

import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.config.Config;
import main.model.Storage;
import main.model.person.Person;
import main.model.Status;
import main.model.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Main claim class.
 * TODO uncomment the toString lines when other models are up to date.
 */
public abstract class Claim implements Serializable, FullTextSearch, Model {

    private int reference;
    private Person customer;
    private Insurance insurance;

    // must find a better name for this.
    private LocalDate dateOfDamages;
    private LocalDate claimDate;
    private LocalDate lastEdited = null;

    private String desc;
    private String contacts;
    private double amount;
    private double deductible;
    private List images;

    private PaymentStatus paymentStatus;
    private Status status;

    public enum ClaimType {
        PROPERTY, TRAVEL, BOAT, CAR
    }

    public enum PaymentStatus {
        A(Loc.c("payment_status_a")),
        B(Loc.c("payment_status_b")),
        C(Loc.c("payment_status_c"));

        String value;
        PaymentStatus(String value){ this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    /**
     * Claim constructor.
     * @param cb
     */
    public Claim(ClaimBuilder cb)
    {
        /** #YOLO */
        reference = reference++;
        customer = cb.getCustomer();
        insurance = cb.getInsurance();
        dateOfDamages = cb.getDateOfDamages();
        claimDate = cb.getClaimDate();
        desc = cb.getDesc();
        contacts = cb.getContacts();
        amount = cb.getAmount();
        deductible = cb.getDeductible();
        images = cb.getImages();
    }

    /* Setters */
    public void setDateOfDamages(LocalDate dateOfDamages)
    {
        this.dateOfDamages = dateOfDamages;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * Claim payment status.
     * @param paymentStatus
     */
    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Claim status.
     * @param status
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     * Setting the contacts info for one witness.
     * @param contacts
     */
    public void setContacts(String contacts)
    {
        this.contacts = contacts;
    }

    /**
     * Setting the contacts info for multiple witnesses.
     * @param contacts
     */
    public void addContacts(String contacts)
    {
        if(this.contacts == null && this.contacts.isEmpty())
        {
            setContacts(contacts);
        }
        else if(this.contacts != null && !this.contacts.isEmpty())
        {
            this.contacts += "\n" + contacts;
        }
    }


    public void setDeductible(double deductible)
    {
        this.deductible = deductible;
    }

    /* Getters */
    public int getReference()
    {
        return reference;
    }

    public Person getCustomer()
    {
        return customer;
    }

    public Insurance getInsurance()
    {
        return insurance;
    }

    public LocalDate getDateOfDamages()
    {
        return dateOfDamages;
    }

    public LocalDate getClaimDate()
    {
        return claimDate;
    }

    public LocalDate getLastEdited()
    {
        return lastEdited;
    }

    public Status getStatus()
    {
        return status;
    }

    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getContacts()
    {
        return contacts;
    }

    public double getAmount()
    {
        return amount;
    }

    public double getDeductible()
    {
        return deductible;
    }

    public static void saveNew(Claim claim) {((List<Claim>) Storage.getInstance().get(Config.CLAIMS)).add(claim);}

    public abstract ClaimType identify();

    @Override
    public boolean query(String value)
    {
        return (desc != null && desc.contains(value));
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
                        "%s:\t%s\n" +
                        "%s:\t%s\n" +
                        "%s:\t%s\n" +
                        "%s:\t%s\n",
                Loc.c("reference"), reference,
                Loc.c("customer"), customer,
                //Loc.c("insurance"), insurance.getReference(),   Remember to add a new "format-line" above.
                Loc.c("accident_date"), dateOfDamages,
                Loc.c("claim_date"), claimDate,
                Loc.c("last_edited"), lastEdited,
                Loc.c("description"), desc,
                Loc.c("contacts"), contacts,
                Loc.c("amount"), amount,
                Loc.c("payment_status"), paymentStatus
        );
    }

}