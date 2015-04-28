package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.time.LocalDate;

public class Boat extends Vehicle implements Serializable
{
    private int length;

    private Propulsion propulsion;
    private Type type;

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
        private String licencePlate;

        private Person owner = null;
        private int modelYear = 2000;
        private LocalDate firstTimeRegistered = null;
        private int length = 0;
        private int horsePower = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;

        public Builder(Person customer, String licencePlate)
        {
            super.customer(customer);
            this.licencePlate = licencePlate;
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

        public Builder firstTimeRegistered(LocalDate date)
        {
            firstTimeRegistered = date;
            return this;
        }

        public Boat build()
        {
            return new Boat(this);
        }
    }


    private Boat(Builder builder)
    {
        super(builder);
        setOwner(builder.owner);
        setLicencePlate(builder.licencePlate);
        setModelYear(builder.modelYear);
        setFirstTimeRegistered(builder.firstTimeRegistered);
        setHorsePower(builder.horsePower);

        length = builder.length;
        type = builder.type;
        propulsion = builder.propulsion;
    }


    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setType(Type type)
    {
        this.type = type;
    }


    public String getType()
    {
        return type.getValue();
    }

    public String getPropulsion()
    {
        return propulsion.getValue();
    }

    public void setPropulsion(Propulsion propulsion)
    {
        this.propulsion = propulsion;
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
