package main.model.claim;

import javafx.scene.image.Image;
import main.localization.Loc;
import main.model.Status;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.time.LocalDate;
import java.util.List;


public abstract class ClaimBuilder<T, E>  {

    private int reference;
    private Person customer;
    private Insurance insurance;

    private LocalDate dateOfDamages = LocalDate.now();
    private LocalDate claimDate = LocalDate.now();

    private String desc = "";
    private String contacts = "";
    private double amount = 0;
    private double deductible = 0;
    private List<Image> images;

    private Status status = Status.INCOMPLETE;

    public ClaimBuilder(Person customer, Insurance insurance)
    {
        this.customer = customer;
        this.insurance = insurance;
    }

    /* Setters */

    public T addImage(Image image)
    {
        images.add(image);
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

    public List<Image> getImages()
    {
        return images;
    }

    public Status getStatus()
    {
        return status;
    }


    /* Abstract */
    public abstract E build();
}
