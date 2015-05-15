package main.model.insurance.vehicle;

import main.model.claim.Claim;
import main.model.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Vehicle.java
 */
public abstract class Vehicle<C extends Claim> extends Insurance<C>
        implements Serializable
{
    private String licensePlate;
    private String owner;
    private int horsePower;
    private int modelYear;
    private LocalDate registration;

    public Vehicle(VehicleBuilder vb)
    {
        super(vb);

        if (vb.getOwner().equals("")) {
            setOwner(vb.getCustomer().getName());
        } else {
            setOwner(vb.getOwner());
        }

        setLicensePlate(vb.getLicensePlate());
        setModelYear(vb.getModelYear());
        setHorsePower(vb.getHorsePower());
        setRegistration(vb.getRegistration());
    }

    /* SETTERS */
    public void setOwner(String owner)
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

    public void setHorsePower(int hk)
    {
        this.horsePower = hk;
    }

    /* GETTERS */
    public String getOwner()
    {
        return owner;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public int getHorsePower()
    {
        return horsePower;
    }

    public int getModelYear()
    {
        return modelYear;
    }

    public LocalDate getRegistration()
    {
        return registration;
    }

    /* OVERRIDES */
    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (licensePlate != null && licensePlate.toLowerCase().contains(value.toLowerCase()));
    }

}
