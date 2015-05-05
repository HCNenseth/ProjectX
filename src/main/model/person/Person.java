package main.model.person;

import main.localization.Loc;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.Status;
import main.model.claim.Claim;
import main.model.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Person class.
 */
public class Person implements Serializable, FullTextSearch, Model
{

    private String firstname;
    private String lastname;

    private String streetAddress;
    private String city;
    private String postalCode;
    private LocalDate dateOfBirth;

    private Status status;

    private List<Insurance> insurances;
    private List<Claim> claims;

    /**
     * Inner builder class, following the Builder pattern.
     */
    public static class Builder
    {
        private String firstname; // required
        private String lastname; // required

        // optional
        private String streetAddress = null;
        private String city = null;
        private String postalCode = null;
        private LocalDate dateOfBirth = null;
        private Status status = Status.ACTIVE;

        /**
         * Constructor with the required params.
         * @param firstname
         * @param lastname
         */
        public Builder(String firstname, String lastname)
        {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        /**
         * Setter for streetAddress member.
         * @param val
         * @return
         */
        public Builder streetAddress(String val)
        {
            streetAddress = val;
            return this;
        }

        /**
         * Setter for city member.
         * @param val
         * @return
         */
        public Builder city(String val)
        {
            city = val;
            return this;
        }

        /**
         * Setter for postal code.
         * @param val
         * @return
         */
        public Builder postalCode(String val)
        {
            postalCode = val;
            return this;
        }

        /**
         * Setter for dob.
         * @param val
         * @return
         */
        public Builder dateOfBirth(LocalDate val)
        {
            dateOfBirth = val;
            return this;
        }

        /**
         * Setter for status.
         * @param val
         * @return
         */
        public Builder status(Status val)
        {
            status = val; return this;
        }

        /**
         * Builder build method for creating the person object.
         * @return
         */
        public Person build()
        {
            return new Person(this);
        }
    }

    /**
     * Person class main and only constructur, requires Builder object.
     * @param builder
     */
    private Person(Builder builder)
    {
        firstname = builder.firstname;
        lastname = builder.lastname;
        dateOfBirth = builder.dateOfBirth;
        streetAddress = builder.streetAddress;
        postalCode = builder.postalCode;
        city = builder.city;
        status = builder.status;

        insurances = new LinkedList<>();
        claims = new LinkedList<>();
    }

    /**
     * Get insurances from person.
     * @return
     */
    public List<Insurance> getInsurances()
    {
        return insurances;
    }

    /**
     * Get claims from person.
     * @return
     */
    public List<Claim> getClaims()
    {
        return claims;
    }

    /**
     * Add insurance to person object.
     * @param insurance
     */
    public void addInsurance(Insurance insurance)
    {
        insurances.add(insurance);
    }

    public void addClaim(Claim claim)
    {
        claims.add(claim);
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getName()
    {
        return String.format("%s %s", firstname, lastname);
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getStreetAddress()
    {
        return streetAddress;
    }

    public String getCity()
    {
        return city;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getFullName() { return firstname + " " + lastname; }

    /**
     * Query method used for search.
     * @param value
     * @return
     */
    public boolean query(String value)
    {
        return firstname.contains(value)
                || lastname.contains(value)
                || (streetAddress != null && streetAddress.contains(value))
                || (city != null && city.contains(value))
                || (postalCode != null && postalCode.contains(value));
    }

    /**
     * Mandatory method for returning staus.
     * @return
     */
    @Override
    public Status getStatus()
    {
        return status;
    }

    public String toString()
    {
        return String.format(
                "\n%s\t%s" +
                "\n%s\t%s" +
                "\n%s\t%s" +
                "\n%s\t%s" +
                "\n%s\t%s",
                Loc.c("firstname"), firstname,
                Loc.c("lastname"), lastname,
                Loc.c("street_address"), streetAddress,
                Loc.c("postal_code"), postalCode,
                Loc.c("city"), city
        );
    }

    @Override
    public ModelType getModelType() { return ModelType.PERSON; }

    // TODO override equals and hashcode
}
