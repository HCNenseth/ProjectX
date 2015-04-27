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
    public Contents(Builder ib) {
        super(ib);
    }

    public static class Builder extends InsuranceBuilder<Builder, Contents>
    {



        @Override
        public Contents build() {
            return new Contents(this);
        }
    }


    @Override
    public ConcreteType identify() {
        return ConcreteType.CONTENTS;
    }
}
