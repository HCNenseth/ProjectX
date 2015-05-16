package main.model.claim.travel;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;


/**
 * TravelClaim.java
 */
public class TravelClaim extends Claim
{

    private Travel.Continent continent;
    private Type type;

    public enum Type
    {
        A(Loc.c("claim_travel_a")),
        B(Loc.c("claim_travel_b")),
        C(Loc.c("claim_travel_c"));

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
            return value;
        }

    }

    /**
     * The type Builder.
     */
    public static class Builder extends ClaimBuilder<Builder, TravelClaim>
    {
        private Travel.Continent continent = Travel.Continent.A;
        private Type type = Type.A;

        /**
         * Instantiates a new Builder.
         *
         * @param customer the customer
         * @param insurance the insurance
         */
        public Builder(Person customer, Insurance insurance)
        {
            super(customer, insurance);
        }

        /**
         * Continent builder.
         *
         * @param continent the continent
         * @return the builder
         */
        public Builder continent(Travel.Continent continent)
        {
            this.continent = continent;
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

        @Override
        public TravelClaim build()
        {
            return new TravelClaim(this);
        }
    }

    private TravelClaim(Builder builder)
    {
        super(builder);
        continent = builder.continent;
        type = builder.type;
    }


    /**
     * Gets continent.
     *
     * @return the continent
     */
    public Travel.Continent getContinent()
    {
        return continent;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType()
    {
        return type;
    }

    /**
     * Sets continent.
     *
     * @param continent the continent
     */
    public void setContinent(Travel.Continent continent)
    {
        this.continent = continent;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /* OVERRIDES */
    @Override
    public ClaimType identify()
    {
        return ClaimType.TRAVEL;
    }

    @Override
    public ModelType getModelType()
    {
        return ModelType.CLAIM;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().toLowerCase().contains(value.toLowerCase()));
    }
}
