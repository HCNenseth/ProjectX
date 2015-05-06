package main.model.claim;

import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Claim implements Serializable, FullTextSearch, Model {

    private int reference;
    private Person customer;
    private Insurance insurance;

    private LocalDate accidentDate;
    private LocalDate claimDate;
    private LocalDate lastEdited = null;

    private String desc;
    private String contacts;
    private double amount;

    private PaymentStatus paymentStatus;
    private Status status;

    public enum PaymentStatus {
        A(Loc.c("payment_status_a")),
        B(Loc.c("payment_status_b")),
        C(Loc.c("payment_status_c"));

        String value;
        PaymentStatus(String value){ this.value = value; }

        public String getValue() { return value; }
    }

    /**
     * Claim constructor.
     * @param cb
     */
    public Claim(ClaimBuilder cb)
    {
        reference = reference++;
        customer = cb.getCustomer();
        insurance = cb.getInsurance();
        accidentDate = cb.getAccidentDate();
        claimDate = cb.getClaimDate();
        desc = cb.getDesc();
        contacts = cb.getContacts();
        amount = cb.getAmount();
        paymentStatus = cb.getPaymentStatus();
    }

    /* Setters */
    public void setAccidentDate(LocalDate accidentDate)
    {
        this.accidentDate = accidentDate;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public void setContacts(String contacts)
    {
        this.contacts = contacts;
    }

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

    public LocalDate getAccidentDate()
    {
        return accidentDate;
    }

    public LocalDate getClaimDate()
    {
        return claimDate;
    }

    public LocalDate getLastEdited()
    {
        return lastEdited;
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
                        "%s:\t%s\n" +
                        "%s:\t%s\n",
                Loc.c("reference"), reference,
                Loc.c("customer"), customer,
                Loc.c("insurance"), insurance.getReference(),   // just random name for insurance ref-number.
                Loc.c("accident_date"), accidentDate,
                Loc.c("claim_date"), claimDate,
                Loc.c("last_edited"), lastEdited,
                Loc.c("description"), desc,
                Loc.c("contacts"), contacts,
                Loc.c("amount"), amount,
                Loc.c("payment_status"), paymentStatus
        );
    }

}