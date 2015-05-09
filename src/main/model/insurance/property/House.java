package main.model.insurance.property;

import main.localization.Loc;
import main.model.claim.property.PropertyClaim;
import main.model.person.Person;
import main.model.insurance.InsuranceType;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


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
        protected String streetAddress;
        protected String postalCode;
        protected String city = "Unknown";
        protected int year = -1;
        protected int squareMeter = -1;
        protected Type type = Type.A;
        protected Material material = Material.A;
        protected Standard standard = Standard.A;

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
        setMaterial(builder.material);
        setSquareMeter(builder.squareMeter);
        setStandard(builder.standard);
        setCity(builder.city);
        setType(builder.type);
        setYear(builder.year);
    }

    /* GETTERS */
    public House.Type getType() { return type; }

    /* SETTERS */
    public void setType(House.Type type) { this.type = type; }

    @Override
    public InsuranceType identify()
    {
        return InsuranceType.HOUSE;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (getStreetAddress() != null && getStreetAddress().contains(value))
                || (getPostalCode() != null && getPostalCode().contains(value))
                || (getCity() != null && getCity().contains(value))
                || (getType() != null && getType().getValue().contains(value))
                || (getMaterial() != null && getMaterial().getValue().contains(value))
                || (getStandard() != null && getStandard().getValue().contains(value));
    }
}
