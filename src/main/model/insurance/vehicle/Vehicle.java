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
    private String licensePlate;
    private Person owner;
    private int horsePower;
    private int modelYear;
    private LocalDate registration;

    public Vehicle(VehicleBuilder ib)
    {
        super(ib);
        setOwner(ib.getOwner());
        setLicensePlate(ib.getLicensePlate());
        setModelYear(ib.getModelYear());
        setHorsePower(ib.getHorsePower());
    }

    public Person getOwner() {
        return owner;
    }

    // SETTERS
    public void setOwner(Person owner)
    {
        this.owner = owner;
    }

    public void setRegistration(LocalDate date)
    {
        registration = date;
    }

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public void setModelYear(int year)
    {
        this.modelYear = year;
    }

    public void setHorsePower(int hk){this.horsePower = hk; }

    // GETTERS
    public String getLicensePlate() {
        return licensePlate;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getModelYear() {
        return modelYear;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (licensePlate != null && licensePlate.contains(value));
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                "\n%s\t%s\n%s\t%s",
                Loc.c("owner"), owner,
                Loc.c("licence_plate"), licensePlate
        );
    }

}
