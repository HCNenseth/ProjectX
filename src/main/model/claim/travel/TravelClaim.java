package main.model.claim.travel;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;

// TODO Override toString

public class TravelClaim extends Claim {

    private Travel.Continent continent;
    private Type type;

    public enum Type
    {
        A(Loc.c("claim_travel_a")),
        B(Loc.c("claim_travel_b")),
        C(Loc.c("claim_travel_c"));

        String value;

        Type(String value){ this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return value; }

    }

    public static class Builder extends ClaimBuilder<Builder, TravelClaim>
    {

        private Travel.Continent continent;
        private Type type = null;

        public Builder(Person customer, Insurance insurance)
        {
            super(customer, insurance);
        }

        public Builder continent(Travel.Continent continent)
        {
            this.continent = continent;
            return this;
        }

        public Builder type(Type type)
        {
            this.type = type;
            return this;
        }

        @Override
        public TravelClaim build() {
            return new TravelClaim(this);
        }
    }

    private TravelClaim(Builder builder)
    {
        super(builder);
        continent = builder.continent;
        type = builder.type;
    }


    /* GETTERS */
    public Travel.Continent getContinent()
    {
        return continent;
    }

    public Type getType()
    {
        return type;
    }

    /* SETTERS */
    public void setContinent(Travel.Continent continent)
    {
        this.continent = continent;
    }
    public void setType(Type type)
    {
        this.type = type;
    }

    @Override
    public ClaimType identify() { return ClaimType.TRAVEL; }

    @Override
    public ModelType getModelType() {
        return ModelType.CLAIM;
    }
}
