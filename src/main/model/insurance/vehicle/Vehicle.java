package main.model.insurance.vehicle;

import main.localization.Loc;
import main.model.person.Person;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Vehicle extends Insurance implements Serializable
{
    private Person owner;
    private String regNr;
    private String regNrRule;

    public Vehicle(InsuranceBuilder ib, Person owner, String regNr)
    {
        super(ib);
        this.owner = owner;
        this.regNr = regNr;
    }

    protected void setRegNrRule(String regNrRule)
    {
        this.regNrRule = regNrRule;
    }

    public String getRegNrRule()
    {
        return regNrRule;
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (regNr != null && regNr.contains(value));
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                "\n%s\t%s\n%s\t%s",
                Loc.get("owner"), owner,
                Loc.get("reg_number"), regNr
        );
    }

}
