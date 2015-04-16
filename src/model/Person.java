package model;

import model.insurance.Insurance;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Person implements Serializable
{

    private final String firstname;
    private final String lastname;

    private final String streetAddress;
    private final String city;
    private final String postalCode;
    private final Calendar dateOfBirth;

    private List<Insurance> insurances;
    private List<Claim> claims;

    public static class Builder
    {
        private String firstname;       // required
        private String lastname;        // required

        // optional
        private String streetAddress = null;
        private String city = null;
        private String postalCode = null;
        private Calendar dateOfBirth = null;

        public Builder(String firstname, String lastname)
        {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public Builder street(String val)
        {
            streetAddress = val;
            return this;
        }

        public Builder city(String val)
        {
            city = val;
            return this;
        }

        public Builder postalCode(String val)
        {
            postalCode = val;
            return this;
        }

        public Builder dateOfBirth(Calendar val)
        {
            dateOfBirth = val;
            return this;
        }

        public Person build()
        {
            return new Person(this);
        }
    }

    public Person(Builder builder)
    {
        firstname = builder.firstname;
        lastname = builder.lastname;
        dateOfBirth = builder.dateOfBirth;
        streetAddress = builder.streetAddress;
        postalCode = builder.postalCode;
        city = builder.city;

        insurances = new LinkedList<>();
        claims = new LinkedList<>();
    }

    public String toString(){
        return firstname + " " + lastname + " " + streetAddress + " " + postalCode + " " + city;
    }

    public List<Insurance> getInsurances()
    {
        return insurances;
    }

    public List<Claim> getClaims()
    {
        return claims;
    }

    public void setInsurance(Insurance insurance)
    {
        insurances.add(insurance);
    }
}
