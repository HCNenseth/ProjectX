package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.person.Person;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by HansChristian on 15.04.2015.
 */
public abstract class Vehicle extends Insurance implements Serializable
{
    private String licencePlate;
    private Person owner;
    private int horsePower;
    private int modelYear;
    private LocalDate firstTimeRegistered;

    public Vehicle(InsuranceBuilder ib)
    {
        super(ib);
    }

    public Person getOwner() {
        return owner;
    }

    // SETTERS
    public void setOwner(Person owner)
    {
        this.owner = owner;
    }

    public void setFirstTimeRegistered(LocalDate date)
    {
        firstTimeRegistered = date;
    }

    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }

    public void setModelYear(int year)
    {
        this.modelYear = year;
    }

    // GETTERS


    public String getLicencePlate() {
        return licencePlate;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getModelYear() {
        return modelYear;
    }

    public LocalDate getFirstTimeRegistered() {
        return firstTimeRegistered;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (licencePlate != null && licencePlate.contains(value));
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                "\n%s\t%s\n%s\t%s",
                Loc.get("owner"), owner,
                Loc.get("licence_plate"), licencePlate
        );
    }

}
