package main.model.claim.property;

import main.localization.Loc;
import main.model.Model;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.time.LocalDate;

/**
 * Created by HansPetter on 07.05.2015.
 */
public class PropertyClaim extends Claim {

    private ClaimType type;

    public enum ClaimType {
        A(Loc.c("claim_property_a")),
        B(Loc.c("claim_property_b")),
        C(Loc.c("claim_property_c"));

        String value;

        ClaimType(String value){ this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return value; }

    }

    public static class Builder extends ClaimBuilder<Builder, PropertyClaim> {

        private ClaimType type;

        public Builder(Person customer, Insurance insurance) {
            super(customer, insurance);
        }

        public Builder type(ClaimType type)
        {
            this.type = type;
            return this;
        }

        @Override
        public PropertyClaim build() {
            return new PropertyClaim(this);
        }
    }

    /**
     * Claim constructor.
     *
     * @param builder
     */
    public PropertyClaim(Builder builder) {
        super(builder);
        type = builder.type;
    }

    @Override
    public ModelType getModelType() {
        return null;
    }
}
