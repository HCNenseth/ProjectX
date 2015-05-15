package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.claim.vehicle.CarClaim;
import main.model.insurance.InsuranceType;
import main.model.person.Person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Car.java
 */
public class Car extends Vehicle<CarClaim> implements Serializable
{
    private int mileage;
    private Type type;
    private Propulsion propulsion;

    public enum Propulsion {
        A(Loc.c("car_propulsion_a")),
        B(Loc.c("car_propulsion_b")),
        C(Loc.c("car_propulsion_c"));

        String value;

        Propulsion(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return value; }
    }

    public enum Type {
        A(Loc.c("car_type_a")),
        B(Loc.c("car_type_b")),
        C(Loc.c("car_type_c")),
        D(Loc.c("car_type_d"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends VehicleBuilder<Builder>
    {
        private int mileage = 0;
        private Type type = Type.A;
        private Propulsion propulsion = Propulsion.A;

        public Builder(Person customer, String licencePlate)
        {
            super.licensePlate(licencePlate);
            super.customer(customer);
        }

        public Builder mileage(int val)
        {
            this.mileage = val;
            return this;
        }

        public Builder type(Type val)
        {
            type = val;
            return this;
        }

        public Builder propulsion(Propulsion val)
        {
            propulsion = val;
            return this;
        }

        public List<CarClaim> getClaimsList()
        {
            return new LinkedList<>();
        }

        public Car build()
        {
            return new Car(this);
        }
    }

    private Car(Builder builder)
    {
        super(builder);
        mileage = builder.mileage;
        type = builder.type;
        propulsion = builder.propulsion;
    }

    /* SETTERS */
    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public void setPropulsion(Propulsion propulsion)
    {
        this.propulsion = propulsion;
    }

    /* GETTERS */
    public int getMileage()
    {
        return mileage;
    }

    public Type getType()
    {
        return type;
    }

    public Propulsion getPropulsion()
    {
        return propulsion;
    }

    public int getBonus()
    {
        return CarBonus.getBonus(this);
    }

    /* OVERRIDES */
    @Override
    public InsuranceType identify()
    {
        return InsuranceType.CAR;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().toLowerCase().contains(value.toLowerCase()))
                || (propulsion != null && propulsion.getValue().toLowerCase().contains(value.toLowerCase()));
    }
}
