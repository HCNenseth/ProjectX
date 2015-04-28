package main.model.insurance.travel;

import main.localization.Loc;
import main.model.insurance.Type;
import main.model.person.Person;
import main.model.insurance.ConcreteType;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import javax.management.ValueExp;
import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Travel extends Insurance implements Serializable
{

    private final Continent continent;

    public enum Continent
    {
        A(Loc.get("continent_a")),
        B(Loc.get("continent_b")),
        C(Loc.get("continent_c")),
        D(Loc.get("continent_d")),
        E(Loc.get("continent_e")),
        F(Loc.get("continent_f"));

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
        continent = builder.continent;
    }

    public ConcreteType identify()
    {
        return ConcreteType.TRAVEL;
    }

    public boolean query(String value)
    {
        return super.query(value);
    }

    // TODO override equals and hashcode
}
