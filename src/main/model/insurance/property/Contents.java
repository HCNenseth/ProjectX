package main.model.insurance.property;

import main.model.insurance.ConcreteType;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansPetter on 27.04.2015.
 */
public class Contents extends Insurance implements Serializable {


    /**
     * Insurance constructor.
     *
     * @param ib
     */
    public Contents(InsuranceBuilder ib) {
        super(ib);
    }

    public static class InsuranceBuilder<Builder, Contents>
    {

    }


    @Override
    public ConcreteType identify() {
        return ConcreteType.CONTENTS;
    }
}
