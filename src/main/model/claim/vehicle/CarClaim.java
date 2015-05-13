package main.model.claim.vehicle;

import main.config.Config;
import main.localization.Loc;
import main.model.claim.Claim;
import main.model.claim.ClaimBuilder;
import main.model.insurance.Insurance;
import main.model.person.Person;

import java.io.File;

/**
 * CarClaim.java
 */
public class CarClaim extends Claim
{
    private Type type;
    private String damageReportFileName;

    public enum Type
    {
        A(Loc.c("claim_car_a")),
        B(Loc.c("claim_car_b")),
        C(Loc.c("claim_car_c"));

        String value;

        Type(String value){ this.value = value; }

        public String getValue(){ return value; }

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends ClaimBuilder<Builder, CarClaim>
    {
        private Type type = Type.A;
        private String damageReport = "";

        public Builder(Person customer, Insurance insurance) {
            super(customer, insurance);
        }

        public Builder type(Type type)
        {
            this.type = type;
            return this;
        }

        public Builder damageReport(String damageReport)
        {
            this.damageReport = damageReport;
            return this;
        }

        @Override
        public CarClaim build()
        {
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
        damageReportFileName = builder.damageReport;
    }

    /* GETTERS */
    public Type getType()
    {
        return type;
    }

    public String getDamageReportFileName()
    {
        return damageReportFileName;
    }

    public File getDamageReportFile()
    {
        return new File(Config.UPLOADS + getFilePathImage());
    }

    /* SETTERS */
    public void setType(Type type)
    {
        this.type = type;
    }

    public void setDamageReportFileName(String damageReportFileName)
    {
        this.damageReportFileName = damageReportFileName;
    }

    /* OVERRIDES */
    @Override
    public ClaimType identify()
    {
        return ClaimType.CAR;
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
