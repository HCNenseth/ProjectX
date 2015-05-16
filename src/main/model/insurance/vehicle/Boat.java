package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.claim.vehicle.BoatClaim;
import main.model.insurance.InsuranceType;
import main.model.person.Person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Boat.java
 */
public class Boat extends Vehicle<BoatClaim> implements Serializable
{
    private int length;

    private Propulsion propulsion;
    private Type type;

    /**
     * The enum Propulsion.
     */
    public enum Propulsion
    {
        /**
         * The A.
         */
        A(Loc.c("boat_propulsion_a")),
        /**
         * The B.
         */
        B(Loc.c("boat_propulsion_b")),
        /**
         * The C.
         */
        C(Loc.c("boat_propulsion_c"));

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
            return getValue();
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
        A(Loc.c("boat_type_a")),
        /**
         * The B.
         */
        B(Loc.c("boat_type_b")),
        /**
         * The C.
         */
        C(Loc.c("boat_type_c")),
        /**
         * The D.
         */
        D(Loc.c("boat_type_d"));

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
            return getValue();
        }
    }

    /**
     * The type Builder.
     */
    public static class Builder extends VehicleBuilder<Builder>
    {
        private int length = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;

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
         * Length builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder length(int val)
        {
            length = val;
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

        public List<BoatClaim> getClaimsList()
        {
            return new LinkedList<>();
        }

        public Boat build()
        {
            return new Boat(this);
        }
    }


    private Boat(Builder builder)
    {
        super(builder);
        length = builder.length;
        type = builder.type;
        propulsion = builder.propulsion;
    }

    /* GETTERS */

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength()
    {
        return length;
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
     * Sets length.
     *
     * @param length the length
     */
/* SETTERS */
    public void setLength(int length)
    {
        this.length = length;
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

    /* OVERRIDES */
    @Override
    public InsuranceType identify()
    {
        return InsuranceType.BOAT;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().toLowerCase().contains(value.toLowerCase()))
                || (propulsion != null && propulsion.getValue().toLowerCase().contains(value.toLowerCase()));
    }
}
