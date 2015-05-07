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

    private Type type;
    private Image damageReport; // skademeldingsskjema

    public enum Type {
        A(Loc.c("claim_car_a")),
        B(Loc.c("claim_car_b")),
        C(Loc.c("claim_car_c"));

        String value;

        Type(String value){ this.value = value; }

        public String getValue(){ return value; }

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends ClaimBuilder<Builder, CarClaim> {

        private Type type;
        private Image damageReport;

        public Builder(Person customer, Insurance insurance, LocalDate accidentDate) {
            super(customer, insurance, accidentDate);
        }

        public Builder type(Type type)
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
