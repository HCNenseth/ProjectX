package main.model.claim.travel;

import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.insurance.travel.Travel;
import main.model.person.Person;

import java.time.LocalDate;

public class TravelClaim extends Claim {

    private Travel.Continent continent;

    public static class Builder extends ClaimBuilder<Builder, TravelClaim>
    {

        private Travel.Continent continent;

        public Builder(Person customer, Insurance insurance, LocalDate accidentDate, Travel.Continent continent)
        {
            super(customer, insurance, accidentDate);
            this.continent = continent;
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
    }

    @Override
    public ModelType getModelType() {
        return null;
    }
}
