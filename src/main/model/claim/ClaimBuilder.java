package main.model.claim;

import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.time.LocalDate;

/**
 * ClaimBuilder.java
 * @param <T> Concrete claim builder class (eg. CarClaim.Builder)
 * @param <E> Concrete claim class (eg CarClaim)
 */
public abstract class ClaimBuilder<T, E>
{
    private int reference;
    private Person customer;
    private Insurance insurance;

    private LocalDate dateOfDamages = LocalDate.now();
    private LocalDate claimDate = LocalDate.now();

    private String desc = "";
    private String contacts = "";
    private double amount = 0;
    private double deductible = 0;
    private String filePathImage = "";

    private Claim.PaymentStatus paymentStatus = Claim.PaymentStatus.A;
    private Status status = Status.ACTIVE;

    public ClaimBuilder(Person customer, Insurance insurance)
    {
        this.customer = customer;
        this.insurance = insurance;
    }

    /* Setters */

    public T image(String filePathImage)
    {
        this.filePathImage = filePathImage;
        return (T) this;
    }

    public T amount(double amount)
    {
        this.amount = amount;
        return (T) this;
    }

    public T deductible(double deductible)
    {
        this.deductible = deductible;
        return (T) this;
    }

    public T contacts(String contacts)
    {
        this.contacts = contacts;
        return (T)this;
    }

    public T desc(String desc)
    {
        this.desc = desc;
        return (T) this;
    }

    public T paymentStatus(Claim.PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
        return (T) this;
    }

    public T status(Status status)
    {
        this.status = status;
        return (T)this;
    }

    public T dateOfDamages(LocalDate dateOfDamages)
    {
        this.dateOfDamages = dateOfDamages;
        return (T) this;
    }

    /**
     * This method is not needed under normal circumstances.
     * To improve the data from the unit tests this has a right to live.
     * @param claimDate
     * @return
     */
    public T claimDate(LocalDate claimDate)
    {
        this.claimDate = claimDate;
        return (T) this;
    }

    /* Getters */

    public Person getCustomer()
    {
        return customer;
    }

    public Insurance getInsurance()
    {
        return insurance;
    }

    public double getAmount()
    {
        return amount;
    }

    public double getDeductible() { return deductible; }

    public String getContacts()
    {
        return contacts;
    }

    public String getDesc()
    {
        return desc;
    }

    public LocalDate getDateOfDamages()
    {
        return dateOfDamages;
    }

    public LocalDate getClaimDate()
    {
        return claimDate;
    }

    public String getFilePathImage()
    {
        return filePathImage;
    }

    public Claim.PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    public Status getStatus()
    {
        return status;
    }


    /* Abstract */
    public abstract E build();
}
