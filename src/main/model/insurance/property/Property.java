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

    private String postalCode;
    private String city;
    private int year;
    private Material material;
    private int squareMeter;
    private Standard standard;
    private String streetAddress;

    protected void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    protected void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    protected void setCity(String city) {
        this.city = city;
    }

    protected void setYear(int year) {
        this.year = year;
    }

    protected void setMaterial(Material material) {
        this.material = material;
    }

    protected void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }

    protected void setStandard(Standard standard) {
        this.standard = standard;
    }

    protected String getPostalCode() {
        return postalCode;
    }

    protected String getCity() {
        return city;
    }

    protected int getYear() {
        return year;
    }

    protected Material getMaterial() {
        return material;
    }

    protected Standard getStandard() {
        return standard;
    }

    protected String getStreetAddress() {
        return streetAddress;
    }

    public Property(InsuranceBuilder ib) {
        super(ib);
    }


    public enum Material {
        A(Loc.get("property_material_a")),
        B(Loc.get("property_material_b")),
        C(Loc.get("property_material_c"));

        String value;

        Material(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public enum Standard {
        A(Loc.get("property_standard_a")),
        B(Loc.get("property_standard_b")),
        C(Loc.get("property_standard_c"));

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
