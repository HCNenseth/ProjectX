package main.model.claim.vehicle;

import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.person.Person;

// TODO Override toString

public class BoatClaim extends Claim {

    private Type type;

    public enum Type
    {
        A(Loc.c("claim_boat_a")),
        B(Loc.c("claim_boat_b")),
        C(Loc.c("claim_boat_c"));

        String value;

        Type(String value){ this.value = value; }

        public String getValue(){ return value; }

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends ClaimBuilder<Builder, BoatClaim> {

        private Type type;

        public Builder(Person customer, Insurance insurance)
        {
            super(customer, insurance);
        }

        public Builder type(Type type)
        {
            this.type = type;
            return this;
        }


        @Override
        public BoatClaim build() {
            return new BoatClaim(this);
        }

    }

    /**
     * Claim constructor.
     *
     * @param builder
     */
    public BoatClaim(Builder builder) {
        super(builder);
        type = builder.type;
    }

    @Override
    public ClaimType identify() { return ClaimType.BOAT; }

    @Override
    public ModelType getModelType() {
        return ModelType.CLAIM;
    }
}
