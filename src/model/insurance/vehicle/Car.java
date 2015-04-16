package model.insurance.vehicle;

import localization.Loc;
import model.Person;


import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Car extends Vehicle
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

        Propulsion(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    public enum Type
    {
        A(Loc.get("car_type_a")),
        B(Loc.get("car_type_b")),
        C(Loc.get("car_type_c")),
        D(Loc.get("car_type_d"));

        String value;

        Type(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    public static class Builder
    {
        //Required
        private String regNr;
        private Person customer;

        //Optional
        private Person owner = customer;
        private int registrationYear = 1900;
        private int milage = 0;
        private int bonus = 0;
        private String desc = "";
        private Calendar date = null;
        private double amount = 0;
        private double premium = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;

        public Builder(Person customer, String regNr)
        {
            this.customer = customer;
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

        public Builder desc(String val)
        {
            desc = val;
            return this;
        }

        public Builder date(Calendar val)
        {
            date = val;
            return this;
        }

        public Builder premium(double val)
        {
            premium = val;
            return this;
        }

        public Builder amount(double val)
        {
            amount = val;
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
        super(builder.customer, builder.premium, builder.amount, builder.date,builder.desc, builder.owner, builder.regNr);
        registrationYear = builder.registrationYear;
        mileage          = builder.milage;
        bonus            = builder.bonus;
        type             = builder.type;
        propulsion       = builder.propulsion;
    }


    public String getType()
    {
        return type.getValue();
    }

    public String getPropulsion()
    {
        return propulsion.getValue();
    }
}
