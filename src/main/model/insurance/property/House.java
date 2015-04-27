package main.model.insurance.property;

import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */

// TODO consider moving some values up to the parent Property class
public class House extends Property implements Serializable
{

    private House(Builder builder)
    {
        super(builder);

        streetAddress = builder.streetAddress;
        postalCode = builder.postalCode;
        city = builder.city;
        year = builder.year;
        squareMeters = builder.squareMeter;
        type = builder.type;
        material = builder.material;
        standard = builder.standard;
    }

    public void setStreetAddress(String streetAddress) {
        super.streetAddress = streetAddress;
    }

    public void setPostalCode(String postalCode) {
        super.postalCode = postalCode;
    }

    public void setCity(String city) {
        super.city = city;
    }

    public void setYear(int year) {
        super.year = year;
    }

    public void setSquareMeter(int squareMeter) {
        super.squareMeters = squareMeter;
    }

    public void setType(Type type) {
        super.type = type;
    }

    public void setMaterial(Material material) {
        super.material = material;
    }

    public void setStandard(Standard standard) {
        super.standard = standard;
    }

    public ConcreteType identify()
    {
        return ConcreteType.HOUSE;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (streetAddress != null && streetAddress.contains(value))
                || (postalCode != null && postalCode.contains(value))
                || (city != null && city.contains(value))
                || (type != null && type.getValue().contains(value))
                || (material != null && material.getValue().contains(value))
                || (standard != null && standard.getValue().contains(value));
    }


    public static class Builder extends InsuranceBuilder<Builder, House>
    {
        protected String streetAddress;
        protected String postalCode;
        protected String city = "";
        protected int year = 2000;
        protected int squareMeter = 100;
        protected Type type = Type.A;
        protected Material material = Material.A;
        protected Standard standard = Standard.A;

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

        public Builder squareMeter(int squareMeter)
        {
            this.squareMeter = squareMeter; return this;
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

        public String getCity()
        {
            return city;
        }

        public House build()
        {
            return new House(this);
        }
    }


    // TODO override equals and hashcode
}
