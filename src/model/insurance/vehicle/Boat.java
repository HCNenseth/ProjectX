package model.insurance.vehicle;

import localization.Loc;
import model.Person;
import model.insurance.ConcreteType;
import model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Boat extends Vehicle implements Serializable
{
    private final int registrationYear;
    private final int length;
    private final int horsePower;
    private final Propulsion propulsion;
    private final Type type;
    private Person owner;

    public enum Propulsion {
        A(Loc.get("boat_propulsion_a")),
        B(Loc.get("boat_propulsion_b")),
        C(Loc.get("boat_propulsion_c"));

        String value;

        Propulsion(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public enum Type {
        A(Loc.get("boat_type_a")),
        B(Loc.get("boat_type_b")),
        C(Loc.get("boat_type_c")),
        D(Loc.get("boat_type_d"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public static class Builder extends InsuranceBuilder<Builder, Boat>
    {
        private String regNr;

        private Person owner = getCustomer();
        private int registrationYear = 1900;
        private int length = 0;
        private int horsePower = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;

        public Builder(Person customer, String regNr)
        {
            super.customer(customer);
            this.regNr = regNr;
        }

        public Builder registrationYear(int val)
        {
            registrationYear = val;
            return this;
        }

        public Builder length(int val)
        {
            length = val;
            return this;
        }

        public Builder horsePower(int val)
        {
            horsePower = val;
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

        public Builder owner(Person val)
        {
            owner = val;
            return this;
        }

        public Boat build()
        {
            return new Boat(this);
        }
    }


    public Boat(Builder builder)
    {
        super(builder, builder.owner, builder.regNr);
        super.setRegNrRule(Loc.get("car_regnr_rgx"));

        registrationYear = builder.registrationYear;
        length = builder.length;
        horsePower = builder.horsePower;
        type = builder.type;
        propulsion = builder.propulsion;
    }

    public String getType()
    {
        return type.getValue();
    }

    public String getPropulsion()
    {
        return propulsion.getValue();
    }

    public ConcreteType identify()
    {
        return ConcreteType.BOAT;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().contains(value))
                || (propulsion != null && propulsion.getValue().contains(value));
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
