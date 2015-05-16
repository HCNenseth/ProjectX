package main.model.insurance.travel;

import main.localization.Loc;
import main.model.claim.travel.TravelClaim;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;
import main.model.insurance.InsuranceType;
import main.model.person.Person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Travel.java
 */
public class Travel extends Insurance<TravelClaim>
        implements Serializable
{
    private Continent type;

    /**
     * The enum Continent.
     */
    public enum Continent
    {
        /**
         * The A.
         */
        A(Loc.c("continent_a")),
        /**
         * The B.
         */
        B(Loc.c("continent_b")),
        /**
         * The C.
         */
        C(Loc.c("continent_c")),
        /**
         * The D.
         */
        D(Loc.c("continent_d")),
        /**
         * The E.
         */
        E(Loc.c("continent_e")),
        /**
         * The F.
         */
        F(Loc.c("continent_f"));

        /**
         * The Value.
         */
        String value;

        /**
         * Instantiates a new Continent.
         *
         * @param value the value
         */
        Continent(String value)
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
            return value;
        }
    }

    /**
     * The type Builder.
     */
    public static class Builder extends InsuranceBuilder<Builder, Travel>
    {
        private Continent continent = Continent.A;

        /**
         * Instantiates a new Builder.
         *
         * @param customer the customer
         */
        public Builder(Person customer)
        {
            super.customer(customer);
        }

        /**
         * Continent builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder continent(Continent val)
        {
            continent = val;
            return this;
        }

        public List<TravelClaim> getClaimsList()
        {
            return new LinkedList<>();
        }

        public Travel build()
        {
            return new Travel(this);
        }
    }

    private Travel(Builder builder)
    {
        super(builder);
        type = builder.continent;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
/* GETTERS */
    public Continent getType()
    {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
/* SETTERS */
    public void setType(Continent type)
    {
        this.type = type;
    }

    /* OVERRIDES */
    @Override
    public InsuranceType identify()
    {
        return InsuranceType.TRAVEL;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().contains(value));
    }
}
