package main.model.insurance.travel;

import main.localization.Loc;
import main.model.claim.travel.TravelClaim;
import main.model.person.Person;
import main.model.insurance.InsuranceType;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Travel.java
 */
public class Travel extends Insurance<TravelClaim>
        implements Serializable
{
    private Continent type;

    public enum Continent {
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

        public List<TravelClaim> getClaimsList()
        {
            return new LinkedList<>();
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
    public Continent getType()
    {
        return type;
    }

    /* SETTERS */
    public void setType(Continent type)
    {
        this.type = type;
    }

    /* OVERRIDES */
    @Override
    public InsuranceType identify()
    {
        return InsuranceType.TRAVEL;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (type != null && type.getValue().contains(value));
    }
}
