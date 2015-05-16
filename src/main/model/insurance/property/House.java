package main.model.insurance.property;

import main.localization.Loc;
import main.model.claim.property.PropertyClaim;
import main.model.insurance.InsuranceBuilder;
import main.model.insurance.InsuranceType;
import main.model.person.Person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * House.java
 */
public class House extends Property implements Serializable
{
    private Type type;

    public enum Type
    {
        A(Loc.c("house_type_a")),
        B(Loc.c("house_type_b")),
        C(Loc.c("house_type_c"));

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
    public static class Builder extends InsuranceBuilder<Builder, House>
    {
        private String streetAddress;
        private String postalCode;
        private String city = "Unknown";
        private int year = 1950;
        private int squareMeter = 0;
        private Type type = Type.A;
        private Material material = Material.A;
        private Standard standard = Standard.A;
        private double contents = 0;


        /**
         * Instantiates a new Builder.
         *
         * @param customer the customer
         * @param streetAddress the street address
         * @param postalCode the postal code
         */
        public Builder(Person customer, String streetAddress, String postalCode)
        {
            super.customer(customer);
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
        }

        /**
         * City builder.
         *
         * @param city the city
         * @return the builder
         */
        public Builder city(String city)
        {
            this.city = city;
            return this;
        }

        /**
         * Street address.
         *
         * @param val the val
         * @return the builder
         */
        public Builder streetAddress(String val)
        {
            this.streetAddress = val;
            return this;
        }

        /**
         * Postal code.
         *
         * @param val the val
         * @return the builder
         */
        public Builder postalCode(String val)
        {
            this.postalCode = val;
            return this;
        }

        /**
         * Year builder.
         *
         * @param year the year
         * @return the builder
         */
        public Builder year(int year)
        {
            this.year = year;
            return this;
        }

        /**
         * Square meter.
         *
         * @param squareMeter the square meter
         * @return the builder
         */
        public Builder squareMeter(int squareMeter)
        {
            this.squareMeter = squareMeter;
            return this;
        }

        /**
         * Type builder.
         *
         * @param type the type
         * @return the builder
         */
        public Builder type(Type type)
        {
            this.type = type;
            return this;
        }

        /**
         * Material builder.
         *
         * @param material the material
         * @return the builder
         */
        public Builder material(Material material)
        {
            this.material = material;
            return this;
        }

        /**
         * Standard builder.
         *
         * @param standard the standard
         * @return the builder
         */
        public Builder standard(Standard standard)
        {
            this.standard = standard;
            return this;
        }

        /**
         * Contents builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder contents(Double val)
        {
            this.contents = val;
            return this;
        }

        @Override
        public List<PropertyClaim> getClaimsList()
        {
            return new LinkedList<>();
        }

        @Override
        public House build()
        {
            return new House(this);
        }
    }

    /**
     * Constructor
     *
     * @param builder
     */
    private House(Builder builder)
    {
        super(builder);

        setStreetAddress(builder.streetAddress);
        setPostalCode(builder.postalCode);
        setCity(builder.city);
        setMaterial(builder.material);
        setSquareMeter(builder.squareMeter);
        setStandard(builder.standard);
        setYear(builder.year);
        setContents(builder.contents);
        type = builder.type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public House.Type getType()
    {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(House.Type type)
    {
        this.type = type;
    }

    @Override
    public InsuranceType identify()
    {
        return InsuranceType.HOUSE;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (getStreetAddress() != null && getStreetAddress().toLowerCase().contains(value.toLowerCase()))
                || (getPostalCode() != null && getPostalCode().toLowerCase().contains(value.toLowerCase()))
                || (getCity() != null && getCity().toLowerCase().contains(value.toLowerCase()))
                || (getType() != null && getType().getValue().toLowerCase().contains(value.toLowerCase()));
    }
}
