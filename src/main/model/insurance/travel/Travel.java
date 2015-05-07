package main.model.insurance.travel;

import main.localization.Loc;
import main.model.person.Person;
import main.model.insurance.InsuranceType;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Travel extends Insurance implements Serializable
{

    private Continent type;

    public enum Continent
    {
        A(Loc.c("continent_a")),
        B(Loc.c("continent_b")),
        C(Loc.c("continent_c")),
        D(Loc.c("continent_d")),
        E(Loc.c("continent_e")),
        F(Loc.c("continent_f"));

        String value;

        Continent(String value) { this.value = value;}

        public String getValue() { return value; }

        @Override
        public String toString() { return value; }
    }

    public static class Builder extends InsuranceBuilder<Builder, Travel>
    {
        private Continent continent = Continent.A;

        public Builder(Person customer)
        {
            super.customer(customer);
        }


        public Builder continent(Continent val)
        {
            continent = val;
            return this;
        }

        public Travel build()
        {
            return new Travel(this);
        }
    }

    private Travel(Builder builder)
    {
        super(builder);
        type = builder.continent;
    }

    /* GETTERS */
    public Continent getType() { return type; }

    /* SETTERS */
    public void setType(Continent type) { this.type = type; }

    public InsuranceType identify()
    {
        return InsuranceType.TRAVEL;
    }

    public boolean query(String value)
    {
        return super.query(value);
    }

    // TODO override equals and hashcode
}
