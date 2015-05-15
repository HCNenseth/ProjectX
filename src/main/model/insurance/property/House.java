package main.model.insurance.property;

import main.localization.Loc;
import main.model.claim.property.PropertyClaim;
import main.model.person.Person;
import main.model.insurance.InsuranceType;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * House.java
 */
public class House extends Property implements Serializable
{
    private Type type;

    public enum Type {
        A(Loc.c("house_type_a")),
        B(Loc.c("house_type_b")),
        C(Loc.c("house_type_c"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

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
        private int contents = 0;


        public Builder(Person customer, String streetAddress, String postalCode)
        {
            super.customer(customer);
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
        }

        public Builder city(String city)
        {
            this.city = city; return this;
        }

        public Builder streetAddress(String val)
        {
            this.streetAddress = val;
            return this;
        }

        public Builder postalCode(String val)
        {
            this.postalCode = val;
            return this;
        }

        public Builder year(int year)
        {
            this.year = year; return this;
        }

        public Builder squareMeter(int squareMeter)
        {
            this.squareMeter = squareMeter;
            return this;
        }

        public Builder type(Type type)
        {
            this.type = type;
            return this;
        }

        public Builder material(Material material)
        {
            this.material = material;
            return this;
        }

        public Builder standard(Standard standard)
        {
            this.standard = standard;
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

    /* GETTERS */
    public House.Type getType() { return type; }

    /* SETTERS */
    public void setType(House.Type type) { this.type = type; }

    /* OVERRIDES */
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
