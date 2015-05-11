package main.model.insurance.vehicle;

import main.model.insurance.InsuranceBuilder;

import java.time.LocalDate;

/**
 * VehicleBuilder.java
 */
public abstract class VehicleBuilder<T>
        extends InsuranceBuilder<T, Vehicle>
{
    private String licensePlate = "";
    private LocalDate registration;
    private int horsePower = 0;
    private int modelYear = 1970;

    /* SETTERS */

    public T licensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
        return (T) this;
    }

    public T registration(LocalDate registration)
    {
        this.registration = registration;
        return (T) this;
    }

    public T horsePower(int horsePower)
    {
        this.horsePower = horsePower;
        return (T) this;
    }

    public T modelYear(int modelYear)
    {
        this.modelYear = modelYear;
        return (T) this;
    }

    /* GETTERS */

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public LocalDate getRegistration()
    {
        return registration;
    }

    public int getHorsePower()
    {
        return horsePower;
    }

    public int getModelYear()
    {
        return modelYear;
    }
}
