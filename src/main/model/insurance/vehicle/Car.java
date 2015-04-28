package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.Status;
import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.InsuranceBuilder;
import sun.util.resources.LocaleData;


import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Car extends Vehicle implements Serializable
{

    private int registrationYear;
    private int mileage;
    private int bonus;

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

        @Override
        public String toString() { return value; }
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

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends InsuranceBuilder<Builder, Car>
    {
        //Required
        private String regNr;

        //Optional
        private Person owner = getCustomer();
        private int registrationYear = 1990;
        private int mileage = 0;
        private int bonus = 0;
        private int amount = 0;
        private int premium = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;
        private Status status = Status.ACTIVE;

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


        public Builder mileage(int val)
        {
            this.mileage = val;
            return this;
        }

        public Builder premium(int val)
        {
            premium = val;
            return this;
        }

        public Builder amount(int val)
        {
            amount = val;
            return this;
        }

        public Builder status(Status status)
        {
            this.status = status;
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

    private Car(Builder builder)
    {
        super(builder, builder.owner, builder.regNr);

        registrationYear = builder.registrationYear;
        mileage = builder.mileage;
        bonus = builder.bonus;
        type = builder.type;
        propulsion = builder.propulsion;
    }

    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }

    public void setRegNr(String regNr)
    {
        super.setRegNr(regNr);
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

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public int getMileage() {
        return mileage;
    }

    public int getBonus() {
        return bonus;
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
