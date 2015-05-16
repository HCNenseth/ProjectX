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

    private LocalDate registrationDate;
    private LocalDate lastEdited;

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
         *
         * @param firstname the firstname
         * @param lastname the lastname
         */
        public Builder(String firstname, String lastname)
        {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        /**
         * Setter for streetAddress member.
         *
         * @param val the val
         * @return builder
         */
        public Builder streetAddress(String val)
        {
            streetAddress = val;
            return this;
        }

        /**
         * Setter for city member.
         *
         * @param val the val
         * @return builder
         */
        public Builder city(String val)
        {
            city = val;
            return this;
        }

        /**
         * Setter for postal code.
         *
         * @param val the val
         * @return builder
         */
        public Builder postalCode(String val)
        {
            postalCode = val;
            return this;
        }

        /**
         * Setter for dob.
         *
         * @param val the val
         * @return builder
         */
        public Builder dateOfBirth(LocalDate val)
        {
            dateOfBirth = val;
            return this;
        }

        /**
         * Phone number.
         *
         * @param val the val
         * @return the builder
         */
        public Builder phoneNumber(String val)
        {
            phoneNumber = val;
            return this;
        }

        /**
         * Email builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder email(String val)
        {
            email = val;
            return this;
        }

        /**
         * Setter for status.
         *
         * @param val the val
         * @return builder
         */
        public Builder status(Status val)
        {
            status = val;
            return this;
        }

        /**
         * Builder build method for creating the person object.
         *
         * @return person
         */
        public Person build()
        {
            return new Person(this);
        }
    }

    /**
     * Person class main and only constructor, requires Builder object.
     *
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
        lastEdited = null;
        registrationDate = LocalDate.now();

        insurances = new LinkedList<>();
        claims = new LinkedList<>();
    }

    /**
     * Add insurance to person object.
     *
     * @param insurance the insurance
     */
    public void addInsurance(Insurance insurance)
    {
        insurances.add(insurance);
    }

    /**
     * Add claim.
     *
     * @param claim the claim
     */
    public void addClaim(Claim claim)
    {
        claims.add(claim);
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets street address.
     *
     * @param streetAddress the street address
     */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city)
    {
        this.city = city;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email)
    {
        this.email = email;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status)
    {
        this.status = status;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
        this.lastEdited = LocalDate.now();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId()
    {
        return Integer.toString(id);
    }

    /**
     * Gets registration date.
     *
     * @return the registration date
     */
    public LocalDate getRegistrationDate()
    {
        return registrationDate;
    }

    /**
     * Gets last edited.
     *
     * @return the last edited
     */
    public LocalDate getLastEdited()
    {
        return lastEdited;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return String.format("%s %s", firstname, lastname);
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname()
    {
        return firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * Gets street address.
     *
     * @return the street address
     */
    public String getStreetAddress()
    {
        return streetAddress;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Gets insurances.
     *
     * @return the insurances
     */
    public List<Insurance> getInsurances()
    {
        return insurances;
    }

    /**
     * Gets claims.
     *
     * @return the claims
     */
    public List<Claim> getClaims()
    {
        return claims;
    }

    /**
     * Gets persons.
     *
     * @return the persons
     */
    public static List<Person> getPersons()
    {
        return (List<Person>) Storage.getInstance().get(Config.PERSONS);
    }

    /**
     * Save new.
     *
     * @param person the person
     */
    public static void saveNew(Person person)
    {
        Person.getPersons().add(person);
    }

    /**
     * Sets counter.
     *
     * @param val the val
     */
    public static void setCounter(int val)
    {
        counter += val;
    }

    /**
     * Query method used for search.
     *
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
     *
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

    @Override
    public LocalDate getEdited()
    {
        return lastEdited;
    }
}
