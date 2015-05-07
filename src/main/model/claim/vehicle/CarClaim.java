package main.model.claim.vehicle;

import javafx.scene.image.Image;
import main.localization.Loc;
import main.model.Model;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.time.LocalDate;

public class CarClaim extends Claim {

    private ClaimType type;
    private Image damageReport; // skademeldingsskjema

    public enum ClaimType {
        A(Loc.c("claim_car_a")),
        B(Loc.c("claim_car_b")),
        C(Loc.c("claim_car_c"));

        String value;

        ClaimType(String value){ this.value = value; }

        public String getValue(){ return value; }

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends ClaimBuilder<Builder, CarClaim> {

        private ClaimType type;
        private Image damageReport;

        public Builder(Person customer, Insurance insurance) {
            super(customer, insurance);
        }

        public Builder type(ClaimType type)
        {
            this.type = type;
            return this;
        }

        public Builder damageReport(Image damageReport)
        {
            this.damageReport = damageReport;
            return this;
        }

        @Override
        public CarClaim build() {
            return new CarClaim(this);
        }

    }


    /**
     * Claim constructor.
     *
     * @param builder
     */
    public CarClaim(Builder builder) {
        super(builder);
        type = builder.type;
    }

    @Override
    public ModelType getModelType() {
        return null;
    }
}
