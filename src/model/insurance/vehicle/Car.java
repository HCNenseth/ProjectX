package model.insurance.vehicle;

import localization.Loc;
import model.person.Person;
import model.insurance.ConcreteType;
import model.insurance.InsuranceBuilder;


import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Car extends Vehicle implements Serializable
{

    private final int registrationYear;
    private final int mileage;
    private final int bonus;

    private final Type type;
    private final Propulsion propulsion;

    public enum Propulsion
    {
        A(Loc.get("car_propulsion_a")),
        B(Loc.get("car_propulsion_b")),
        C(Loc.get("car_propulsion_c"));

        String value;

        Propulsion(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public enum Type
    {
        A(Loc.get("car_type_a")),
        B(Loc.get("car_type_b")),
        C(Loc.get("car_type_c")),
        D(Loc.get("car_type_d"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public static class Builder extends InsuranceBuilder<Builder, Car>
    {
        //Required
        private String regNr;

        //Optional
        private Person owner = getCustomer();
        private int registrationYear = 1900;
        private int milage = 0;
        private int bonus = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;

        public Builder(Person customer, String regNr)
        {
            super.customer(customer);
            this.regNr = regNr;
        }

        public Builder registrationYear(int val)
        {
            this.registrationYear = val;
            return this;
        }


        public Builder milage(int val)
        {
            this.milage = val;
            return this;
        }

        public Builder bonus(int val)
        {
            this.bonus = val;
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

        public Car build()
        {
            return new Car(this);
        }
    }

    public Car(Builder builder)
    {
        super(builder, builder.owner, builder.regNr);
        super.setRegNrRule(Loc.get("car_regnr_rgx"));

        registrationYear = builder.registrationYear;
        mileage = builder.milage;
        bonus = builder.bonus;
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
        return ConcreteType.CAR;
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
