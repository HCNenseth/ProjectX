package model.insurance.property;

import model.insurance.Insurance;
import model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Property extends Insurance implements Serializable
{

    public Property(InsuranceBuilder ib) { super(ib); }

    @Override
    public boolean query(String value)
    {
        return super.query(value); // only push upstream
    }
}
