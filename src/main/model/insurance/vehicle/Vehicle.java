package main.model.insurance.vehicle;

import main.model.claim.Claim;
import main.model.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Vehicle.java
 * @param <C>  the type parameter
 */
public abstract class Vehicle<C extends Claim> extends Insurance<C>
        implements Serializable
{
    private String licensePlate;
    private String owner;
    private int horsePower;
    private int modelYear;
    private LocalDate registration;

    /**
     * Instantiates a new Vehicle.
     *
     * @param vb the vb
     */
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

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    /**
     * Sets registration.
     *
     * @param date the date
     */
    public void setRegistration(LocalDate date)
    {
        registration = date;
    }

    /**
     * Sets license plate.
     *
     * @param licensePlate the license plate
     */
    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    /**
     * Sets model year.
     *
     * @param year the year
     */
    public void setModelYear(int year)
    {
        this.modelYear = year;
    }

    /**
     * Sets horse power.
     *
     * @param hk the hk
     */
    public void setHorsePower(int hk)
    {
        this.horsePower = hk;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Gets license plate.
     *
     * @return the license plate
     */
    public String getLicensePlate()
    {
        return licensePlate;
    }

    /**
     * Gets horse power.
     *
     * @return the horse power
     */
    public int getHorsePower()
    {
        return horsePower;
    }

    /**
     * Gets model year.
     *
     * @return the model year
     */
    public int getModelYear()
    {
        return modelYear;
    }

    /**
     * Gets registration.
     *
     * @return the registration
     */
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
