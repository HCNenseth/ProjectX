package main.model.claim;

import main.config.Config;
import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.Status;
import main.model.Storage;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Claim.
 */
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
        PROPERTY(Loc.c("property")),
        TRAVEL(Loc.c("travel")),
        BOAT(Loc.c("boat")),
        CAR(Loc.c("car"));

        String value;

        /**
         * Instantiates a new Claim type.
         *
         * @param value the value
         */
        ClaimType(String value)
        {
            this.value = value;
        }

        /**
         * Gets type.
         *
         * @param value the value
         * @return the type
         */
        public static ClaimType getType(String value)
        {
            for (ClaimType type : values())
                if (type.getValue().equals(value))
                    return type;
            return null;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return getValue();
        }
    }

    /**
     * The enum Payment status.
     */
    public enum PaymentStatus
    {
        A(Loc.c("payment_status_a")),
        B(Loc.c("payment_status_b")),
        C(Loc.c("payment_status_c"));

        String value;

        /**
         * Instantiates a new Payment status.
         *
         * @param value the value
         */
        PaymentStatus(String value)
        {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return getValue();
        }
    }

    /**
     * Claim constructor.
     *
     * @param cb the cb
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

    /***********
     * SETTERS *
     ***********/

    /**
     * Sets date of damages.
     *
     * @param dateOfDamages the date of damages
     */
    public void setDateOfDamages(LocalDate dateOfDamages)
    {
        this.dateOfDamages = dateOfDamages;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets claim date.
     *
     * @param claimDate the claim date
     */
    public void setClaimDate(LocalDate claimDate)
    {
        this.claimDate = claimDate;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets payment status.
     *
     * @param paymentStatus the payment status
     */
    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status)
    {
        this.status = status;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets contacts.
     *
     * @param contacts the contacts
     */
    public void setContacts(String contacts)
    {
        this.contacts = contacts;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Add contacts.
     *
     * @param contacts the contacts
     */
    public void addContacts(String contacts)
    {
        if (this.contacts.equals("")) {
            setContacts(contacts);
        } else {
            this.contacts += "\n" + contacts;
        }
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets file path image.
     *
     * @param filePathImage the file path image
     */
    public void setFilePathImage(String filePathImage)
    {
        this.filePathImage = filePathImage;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets deductible.
     *
     * @param deductible the deductible
     */
    public void setDeductible(double deductible)
    {
        this.deductible = deductible;
        this.lastEdited = LocalDate.now();
    }

    /***********
     * GETTERS *
     ***********/

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId()
    {
        return Integer.toString(id);
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
     * Gets insurance.
     *
     * @return the insurance
     */
    public Insurance getInsurance()
    {
        return insurance;
    }

    /**
     * Gets date of damages.
     *
     * @return the date of damages
     */
    public LocalDate getDateOfDamages()
    {
        return dateOfDamages;
    }

    /**
     * Gets claim date.
     *
     * @return the claim date
     */
    public LocalDate getClaimDate()
    {
        return claimDate;
    }

    /**
     * Gets last edited.
     *
     * @return the last edited
     */
    public LocalDate getLastEdited()
    {
        return lastEdited;
    }

    public Status getStatus()
    {
        return status;
    }

    /**
     * Gets payment status.
     *
     * @return the payment status
     */
    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
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
     * Gets contacts.
     *
     * @return the contacts
     */
    public String getContacts()
    {
        return contacts;
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
     * Gets deductible.
     *
     * @return the deductible
     */
    public double getDeductible()
    {
        return deductible;
    }

    /**
     * Gets file path image.
     *
     * @return the file path image
     */
    public String getFilePathImage()
    {
        return filePathImage;
    }

    /**
     * Gets image file.
     *
     * @return the image file
     */
    public File getImageFile()
    {
        return new File(Config.UPLOADS + getFilePathImage());
    }

    /**********
     * STATIC *
     **********/

    /**
     * Gets claims.
     *
     * @return the claims
     */
    public static List<Claim> getClaims()
    {
        return (List<Claim>) Storage.getInstance().get(Config.CLAIMS);
    }

    /**
     * Save new.
     *
     * @param claim the claim
     */
    public static void saveNew(Claim claim)
    {
        Claim.getClaims().add(claim);
    }

    /**
     * Sets counter.
     *
     * @param val the val
     */
    public static void setCounter(int val)
    {
        counter += val;
    }


    /**
     * Identify claim type.
     *
     * @return the claim type
     */
    public abstract ClaimType identify();

    @Override
    public boolean query(String value)
    {
        return desc.toLowerCase().contains(value.toLowerCase())
                || contacts.toLowerCase().contains(value.toLowerCase())
                || paymentStatus.getValue().toLowerCase().contains(value.toLowerCase())
                || identify().getValue().toLowerCase().contains(value.toLowerCase())
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