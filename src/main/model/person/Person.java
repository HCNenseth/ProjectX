package main.model.person;

import main.config.Config;
import main.model.FullTextSearch;
import main.model.Model;
import main.model.Status;
import main.model.Storage;
import main.model.claim.Claim;
import main.model.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Person.java
 */
public class Person implements Serializable, FullTextSearch, Model
{
    private static int counter = Config.PERSON_COUNTER_START;
    private int id;

    private String firstname;
    private String lastname;

    private String streetAddress;
    private String city;
    private String postalCode;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;

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
        private LocalDate dateOfBirth = null;
        private String streetAddress = "";
        private String city = "";
        private String postalCode = "";
        private String phoneNumber = "";
        private String email = "";
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

        public Builder phoneNumber(String val)
        {
            phoneNumber = val;
            return this;
        }

        public Builder email(String val)
        {
            email = val;
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
     * Person class main and only constructor, requires Builder object.
     * @param builder
     */
    private Person(Builder builder)
    {
        firstname = builder.firstname;
        lastname = builder.lastname;
        dateOfBirth = builder.dateOfBirth;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
        streetAddress = builder.streetAddress;
        postalCode = builder.postalCode;
        city = builder.city;
        status = builder.status;
        id = counter++;

        insurances = new LinkedList<>();
        claims = new LinkedList<>();
    }

    /* ADDERS */
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

    /* SETTERS */
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

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /* GETTERS */
    public String getId()
    {
        return Integer.toString(id);
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

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
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

    /* STATIC */
    public static List<Person> getPersons()
    {
        return (List<Person>)Storage.getInstance().get(Config.PERSONS);
    }

    public static void saveNew(Person person)
    {
        Person.getPersons().add(person);
    }

    public static void setCounter(int val)
    {
        counter += val;
    }

    /* OVERRIDES */
    /**
     * Query method used for search.
     * @param value
     * @return
     */
    @Override
    public boolean query(String value)
    {
        return getName().toLowerCase().contains(value.toLowerCase())
                || streetAddress.toLowerCase().contains(value.toLowerCase())
                || city.toLowerCase().contains(value.toLowerCase())
                || postalCode.toLowerCase().contains(value.toLowerCase())
                || phoneNumber.toLowerCase().contains(value.toLowerCase())
                || email.toLowerCase().contains(value.toLowerCase())
                || getId().toLowerCase().contains(value.toLowerCase());
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

    @Override
    public ModelType getModelType()
    {
        return ModelType.PERSON;
    }

    @Override
    public LocalDate getDate()
    {
        return dateOfBirth;
    }
}
