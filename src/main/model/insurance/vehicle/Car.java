package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.InsuranceBuilder;


import java.io.Serializable;
import java.time.LocalDate;

public class Car extends Vehicle implements Serializable
{
    private int mileage;

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
        private String licencePlate;

        //Optional
        private Person owner = getCustomer();
        private LocalDate registration = null;
        private int modelYear = 2000;
        private int mileage = 0;
        private Propulsion propulsion = Propulsion.A;
        private Type type = Type.A;
        private int horsePower = 100;

        public Builder(Person customer, String licencePlate)
        {
            super.customer(customer);
            this.licencePlate = licencePlate;
        }

        public Builder registration(LocalDate date)
        {
            this.registration = date;
            return this;
        }

        public Builder horsePower(int horsePower)
        {
            this.horsePower = horsePower;
            return this;
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
        super(builder);
        setOwner(builder.owner);
        setLicencePlate(builder.licencePlate);
        setRegistration(builder.registration);
        setModelYear(builder.modelYear);

        mileage = builder.mileage;
        type = builder.type;
        propulsion = builder.propulsion;
    }

    public void setMileage(int mileage)
    {
        this.mileage = mileage;
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

    public int getMileage() {
        return mileage;
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
