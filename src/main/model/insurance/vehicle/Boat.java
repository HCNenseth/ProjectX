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

    public enum Propulsion {
        A(Loc.c("boat_propulsion_a")),
        B(Loc.c("boat_propulsion_b")),
        C(Loc.c("boat_propulsion_c"));

        String value;

        Propulsion(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public enum Type {
        A(Loc.c("boat_type_a")),
        B(Loc.c("boat_type_b")),
        C(Loc.c("boat_type_c")),
        D(Loc.c("boat_type_d"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public static class Builder extends VehicleBuilder<Builder>
    {
        private int length = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;

        public Builder(Person customer, String licencePlate)
        {
            super.licensePlate(licencePlate);
            super.customer(customer);
        }

        public Builder length(int val)
        {
            length = val;
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

    public int getLength()
    {
        return length;
    }

    public Type getType()
    {
        return type;
    }

    public Propulsion getPropulsion()
    {
        return propulsion;
    }

    /* SETTERS */
    public void setLength(int length)
    {
        this.length = length;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

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
