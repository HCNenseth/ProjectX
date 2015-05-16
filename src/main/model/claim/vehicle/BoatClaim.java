package main.model.claim.vehicle;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.person.Person;

/**
 * BoatClaim.java
 */
public class BoatClaim extends Claim
{
    private Type type;

    public enum Type
    {
        A(Loc.c("claim_boat_a")),
        B(Loc.c("claim_boat_b")),
        C(Loc.c("claim_boat_c"));

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
    public static class Builder extends ClaimBuilder<Builder, BoatClaim>
    {
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
        public BoatClaim build()
        {
            return new BoatClaim(this);
        }

    }

    /**
     * Claim constructor.
     *
     * @param builder the builder
     */
    public BoatClaim(Builder builder)
    {
        super(builder);
        type = builder.type;
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

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType()
    {
        return type;
    }

    /* OVERRIDES */
    @Override
    public ClaimType identify()
    {
        return ClaimType.BOAT;
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
