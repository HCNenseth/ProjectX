package main.model.claim;

import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.config.Config;
import main.model.Storage;
import main.model.person.Person;
import main.model.Status;
import main.model.insurance.Insurance;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Claim implements Serializable, FullTextSearch, Model
{
    private static int counter = Config.CLAIM_COUNTER_START;
    private int id;

    private Person customer;
    private Insurance insurance;

    private LocalDate dateOfDamages;
    private LocalDate claimDate;
    private LocalDate lastEdited = null;

    private String desc;
    private String contacts;
    private double amount;
    private double deductible;
    private String filePathImage;

    private PaymentStatus paymentStatus;
    private Status status;

    public enum ClaimType
    {
        PROPERTY(Loc.l("property")),
        TRAVEL(Loc.l("travel")),
        BOAT(Loc.l("boat")),
        CAR(Loc.l("car"));

        String value;

        ClaimType(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public enum PaymentStatus
    {
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
        id = counter++;

        customer = cb.getCustomer();
        insurance = cb.getInsurance();
        dateOfDamages = cb.getDateOfDamages();
        claimDate = cb.getClaimDate();
        desc = cb.getDesc();
        contacts = cb.getContacts();
        amount = cb.getAmount();
        deductible = cb.getDeductible();
        filePathImage = cb.getFilePathImage();
        paymentStatus = cb.getPaymentStatus();
        status = cb.getStatus();

        // connect this claim reference to customer and insurance
        customer.addClaim(this);
        insurance.addClaim(this);
    }

    /* Setters */
    public void setDateOfDamages(LocalDate dateOfDamages)
    {
        this.dateOfDamages = dateOfDamages;
        this.lastEdited = LocalDate.now();
    }

    public void setClaimDate(LocalDate claimDate)
    {
        this.claimDate = claimDate;
        this.lastEdited = LocalDate.now();
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
        this.lastEdited = LocalDate.now();
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
        this.lastEdited = LocalDate.now();
    }

    public void setStatus(Status status)
    {
        this.status = status;
        this.lastEdited = LocalDate.now();
    }

    public void setContacts(String contacts)
    {
        this.contacts = contacts;
        this.lastEdited = LocalDate.now();
    }

    public void addContacts(String contacts)
    {
        if(this.contacts.equals("")) {
            setContacts(contacts);
        } else {
            this.contacts += "\n" + contacts;
        }
        this.lastEdited = LocalDate.now();
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
        this.lastEdited = LocalDate.now();
    }

    public void setFilePathImage(String filePathImage)
    {
        this.filePathImage = filePathImage;
        this.lastEdited = LocalDate.now();
    }

    public void setDeductible(double deductible)
    {
        this.deductible = deductible;
        this.lastEdited = LocalDate.now();
    }

    /* Getters */
    public String getId()
    {
        return Integer.toString(id);
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

    public String getFilePathImage()
    {
        return filePathImage;
    }

    public File getImageFile()
    {
        return new File(Config.UPLOADS + getFilePathImage());
    }

    /* STATIC */
    public static List<Claim> getClaims()
    {
        return (List<Claim>)Storage.getInstance().get(Config.CLAIMS);
    }

    public static void saveNew(Claim claim)
    {
        Claim.getClaims().add(claim);
    }

    public static void setCounter(int val)
    {
        counter += val;
    }


    /* ABSTRACT */
    public abstract ClaimType identify();

    /* OVERRIDES */
    @Override
    public boolean query(String value)
    {
        return desc.toLowerCase().contains(value.toLowerCase())
                || contacts.toLowerCase().contains(value.toLowerCase())
                || paymentStatus.getValue().toLowerCase().contains(value.toLowerCase())
                || getId().contains(value);
    }

    @Override
    public LocalDate getDate()
    {
        return dateOfDamages;
    }

    @Override
    public LocalDate getEdited()
    {
        return lastEdited;
    }
}