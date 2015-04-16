package model.osvold;

import model.Person;

/**
 * Created by HansPetter on 16.04.2015.
 */
final class VehicleBuilder
        <B extends GenericInsuranceBuilder<B>> {

    private Person owner;
    private String regNr;

    public B owner(Person val)
    {
        owner = val;
        return (B)this;
    }

    public B regNr(String val)
    {
        regNr = val;
        return (B)this;
    }

}