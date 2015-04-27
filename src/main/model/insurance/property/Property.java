package main.model.insurance.property;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Created by HansChristian on 15.04.2015.
 */
abstract class Property extends Insurance implements Serializable
{

    protected String postalCode;
    protected String city;
    protected int year;
    protected Material material;
    protected Type type;
    protected int squareMeters;
    protected Standard standard;
    protected String streetAddress;


    public Property(InsuranceBuilder ib) {
        super(ib);
    }

    public enum Type {
        A(Loc.get("house_type_a")),
        B(Loc.get("house_type_b")),
        C(Loc.get("house_type_c"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public enum Material {
        A(Loc.get("house_material_a")),
        B(Loc.get("house_material_b")),
        C(Loc.get("house_material_c"));

        String value;

        Material(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public enum Standard {
        A(Loc.get("house_standard_a")),
        B(Loc.get("house_standard_b")),
        C(Loc.get("house_standard_c"));

        String value;

        Standard(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    @Override
    public boolean query(String value)
    {
        return super.query(value); // only push upstream
    }
}
