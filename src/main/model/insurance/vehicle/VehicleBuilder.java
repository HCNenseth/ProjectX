package main.model.insurance.vehicle;

import main.model.insurance.InsuranceBuilder;

import java.time.LocalDate;

/**
 * VehicleBuilder.java
 * @param <T>  the type parameter
 */
public abstract class VehicleBuilder<T>
        extends InsuranceBuilder<T, Vehicle>
{
    private String owner = "";
    private String licensePlate = "";
    private LocalDate registration;
    private int horsePower = 0;
    private int modelYear = 1970;

    /* SETTERS */

    /**
     * Owner t.
     *
     * @param owner the owner
     * @return the t
     */
    public T owner(String owner)
    {
        this.owner = owner;
        return (T) this;
    }

    /**
     * License plate.
     *
     * @param licensePlate the license plate
     * @return the t
     */
    public T licensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
        return (T) this;
    }

    /**
     * Registration t.
     *
     * @param registration the registration
     * @return the t
     */
    public T registration(LocalDate registration)
    {
        this.registration = registration;
        return (T) this;
    }

    /**
     * Horse power.
     *
     * @param horsePower the horse power
     * @return the t
     */
    public T horsePower(int horsePower)
    {
        this.horsePower = horsePower;
        return (T) this;
    }

    /**
     * Model year.
     *
     * @param modelYear the model year
     * @return the t
     */
    public T modelYear(int modelYear)
    {
        this.modelYear = modelYear;
        return (T) this;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
/* GETTERS */
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
     * Gets registration.
     *
     * @return the registration
     */
    public LocalDate getRegistration()
    {
        return registration;
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
}
