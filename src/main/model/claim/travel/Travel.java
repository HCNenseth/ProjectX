package main.model.claim.travel;

import main.model.Model;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;

/**
 * Created by HansPetter on 06.05.2015.
 */
public class Travel extends Claim {

    private main.model.insurance.travel.Travel.Continent location;

    public static class Builder extends ClaimBuilder<Builder, Travel>
    {

        private main.model.insurance.travel.Travel.Continent location = main.model.insurance.travel.Travel.Continent.A;

        public Builder location(main.model.insurance.travel.Travel.Continent location)
        {
            this.location = location;
            return this;
        }

        @Override
        public Travel build() {
            return new Travel(this);
        }
    }

    private Travel(Builder builder)
    {
        super(builder);
        location = builder.location;
    }

    @Override
    public ModelType getModelType() {
        return null;
    }
}
