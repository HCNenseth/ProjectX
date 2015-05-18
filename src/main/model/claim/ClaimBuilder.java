package main.model.claim;

import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.time.LocalDate;

/**
 * ClaimBuilder.java
 *
 * @param <T>  Concrete claim builder class (eg. CarClaim.Builder)
 * @param <E>  Concrete claim class (eg CarClaim)
 */
public abstract class ClaimBuilder<T, E>
{
    private final Person customer;
    private final Insurance insurance;

    private LocalDate dateOfDamages = LocalDate.now();
    private LocalDate claimDate = LocalDate.now();

    private String desc = "";
    private String contacts = "";
    private double amount = 0;
    private double deductible = 0;
    private String filePathImage = "";

    private Claim.PaymentStatus paymentStatus = Claim.PaymentStatus.A;
    private Status status = Status.ACTIVE;

    /**
     * Instantiates a new Claim builder.
     *
     * @param customer the customer
     * @param insurance the insurance
     */
    public ClaimBuilder(Person customer, Insurance insurance)
    {
        this.customer = customer;
        this.insurance = insurance;
    }

    /* Setters */

    /**
     * Image t.
     *
     * @param filePathImage the file path image
     * @return the t
     */
    public T image(String filePathImage)
    {
        this.filePathImage = filePathImage;
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
     * Contacts t.
     *
     * @param contacts the contacts
     * @return the t
     */
    public T contacts(String contacts)
    {
        this.contacts = contacts;
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
     * Payment status.
     *
     * @param paymentStatus the payment status
     * @return the t
     */
    public T paymentStatus(Claim.PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
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
     * Date of damages.
     *
     * @param dateOfDamages the date of damages
     * @return the t
     */
    public T dateOfDamages(LocalDate dateOfDamages)
    {
        this.dateOfDamages = dateOfDamages;
        return (T) this;
    }

    /**
     * This method is not needed under normal circumstances.
     * To improve the data from the unit tests this has a right to live.
     *
     * @param claimDate the claim date
     * @return t
     */
    public T claimDate(LocalDate claimDate)
    {
        this.claimDate = claimDate;
        return (T) this;
    }

    /***********
     * GETTERS *
     ***********/

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
     * Gets contacts.
     *
     * @return the contacts
     */
    public String getContacts()
    {
        return contacts;
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
     * Gets file path image.
     *
     * @return the file path image
     */
    public String getFilePathImage()
    {
        return filePathImage;
    }

    /**
     * Gets payment status.
     *
     * @return the payment status
     */
    public Claim.PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
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
}
