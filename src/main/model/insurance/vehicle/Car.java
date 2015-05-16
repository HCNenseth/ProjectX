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

    /**
     * The enum Propulsion.
     */
    public enum Propulsion
    {
        /**
         * The A.
         */
        A(Loc.c("car_propulsion_a")),
        /**
         * The B.
         */
        B(Loc.c("car_propulsion_b")),
        /**
         * The C.
         */
        C(Loc.c("car_propulsion_c"));

        /**
         * The Value.
         */
        String value;

        /**
         * Instantiates a new Propulsion.
         *
         * @param value the value
         */
        Propulsion(String value)
        {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return value;
        }
    }

    /**
     * The enum Type.
     */
    public enum Type
    {
        /**
         * The A.
         */
        A(Loc.c("car_type_a")),
        /**
         * The B.
         */
        B(Loc.c("car_type_b")),
        /**
         * The C.
         */
        C(Loc.c("car_type_c")),
        /**
         * The D.
         */
        D(Loc.c("car_type_d"));

        /**
         * The Value.
         */
        String value;

        /**
         * Instantiates a new Type.
         *
         * @param value the value
         */
        Type(String value)
        {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return value;
        }
    }

    /**
     * The type Builder.
     */
    public static class Builder extends VehicleBuilder<Builder>
    {
        private int mileage = 0;
        private Type type = Type.A;
        private Propulsion propulsion = Propulsion.A;

        /**
         * Instantiates a new Builder.
         *
         * @param customer the customer
         * @param licencePlate the licence plate
         */
        public Builder(Person customer, String licencePlate)
        {
            super.licensePlate(licencePlate);
            super.customer(customer);
        }

        /**
         * Mileage builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder mileage(int val)
        {
            this.mileage = val;
            return this;
        }

        /**
         * Type builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder type(Type val)
        {
            type = val;
            return this;
        }

        /**
         * Propulsion builder.
         *
         * @param val the val
         * @return the builder
         */
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

    /**
     * Sets mileage.
     *
     * @param mileage the mileage
     */
    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * Sets propulsion.
     *
     * @param propulsion the propulsion
     */
    public void setPropulsion(Propulsion propulsion)
    {
        this.propulsion = propulsion;
    }

    /**
     * Gets mileage.
     *
     * @return the mileage
     */
    public int getMileage()
    {
        return mileage;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType()
    {
        return type;
    }

    /**
     * Gets propulsion.
     *
     * @return the propulsion
     */
    public Propulsion getPropulsion()
    {
        return propulsion;
    }

    /**
     * Gets bonus.
     *
     * @return the bonus
     */
    public int getBonus()
    {
        return CarBonus.getBonus(this);
    }

    /**
     * Gets bonus premium.
     *
     * @return the bonus premium
     */
    public double getBonusPremium()
    {
        // silly premium bonus calculation
        return getPremium() * (1 - (getBonus()/200d));
    }

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
