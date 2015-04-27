package main.model.insurance.property;

import main.model.insurance.ConcreteType;
import main.model.insurance.InsuranceBuilder;
import main.model.person.Person;

import java.io.Serializable;

/**
 * Created by Hans Petter on 27.04.2015.
 */
public class VacationHouse extends Property implements Serializable {


    public VacationHouse(Builder builder) {
        super(builder);

        super.streetAddress = builder.streetAddress;
        super.postalCode = builder.postalCode;
        super.city = builder.city;
        super.year = builder.year;
        super.squareMeters = builder.squareMeters;
        super.type = builder.type;
        super.material = builder.material;
        super.standard = builder.standard;
    }

    @Override
    public ConcreteType identify() {
        return ConcreteType.VACATION_HOUSE;
    }


    public static class Builder extends InsuranceBuilder<Builder, VacationHouse>
    {
        protected String streetAddress;
        protected String postalCode;
        protected String city = "";
        protected int year = 2000;
        protected int squareMeters = 100;
        protected Type type = Type.A;
        protected Material material = Material.A;
        protected Standard standard = Standard.A;

        public String getStreetAddress()
        {
            return streetAddress;
        }

        public String getCity()
        {
            return city;
        }

        public Builder(Person customer,
                       String streetAddress,
                       String postalCode)
        {
            super.customer(customer);
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
        }

        public Builder city(String city)
        {
            this.city = city; return this;
        }

        public Builder year(int year)
        {
            this.year = year; return this;
        }

        public Builder squareMeters(int squareMeters)
        {
            this.squareMeters = this.squareMeters; return this;
        }

        public Builder type(Type type)
        {
            this.type = type; return this;
        }

        public Builder material(Material material)
        {
            this.material = material; return this;
        }

        public Builder standard(Standard standard)
        {
            this.standard = standard; return this;
        }

        @Override
        public VacationHouse build() {
            return new VacationHouse(this);
        }
    }

}
