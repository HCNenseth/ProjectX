package main.model.insurance.property;

import main.localization.Loc;
import main.model.insurance.InsuranceType;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;
import main.model.person.Person;

import java.io.Serializable;

public class Contents extends Insurance implements Serializable {

    private String streetAddress;
    private String postalCode;
    private String city;
    private int numberOfRooms;
    private int numberOfResidents;
    private Type type;

    public enum Type {
        A(Loc.c("contents_type_a")),
        B(Loc.c("contents_type_b")),
        C(Loc.c("contents_type_c"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    private Contents(Builder ib) {
        super(ib);

        streetAddress = ib.streetAddress;
        postalCode = ib.postalCode;
        city = ib.city;
        numberOfRooms = ib.numberOfRooms;
        numberOfResidents = ib.numberOfResidents;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Contents.Type type)
    {
        this.type = type;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    @Override
    public InsuranceType identify() {
        return InsuranceType.CONTENTS;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (getStreetAddress() != null && getStreetAddress().contains(value))
                || (getPostalCode() != null && getPostalCode().contains(value))
                || (getCity() != null && getCity().contains(value))
                || (getType() != null && getType().getValue().contains(value));
    }

    public static class Builder extends InsuranceBuilder<Builder, Contents>
    {
        private String streetAddress;
        private String postalCode;
        private String city = "Unknown";
        private int numberOfRooms = 1;
        private int numberOfResidents = 1;

        public Builder(Person customer, String streetAddress, String postalCode)
        {
            super.customer(customer);
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
        }

        public Builder city(String city)
        {
            this.city = city;
            return this;
        }

        public Builder numberOfRooms(int numberOfRooms)
        {
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public Builder numberOfResidents(int numberOfResidents)
        {
            this.numberOfResidents = numberOfResidents;
            return this;
        }

        @Override
        public Contents build() {
            return new Contents(this);
        }
    }


}
