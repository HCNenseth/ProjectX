package main.model.insurance.property;

import main.localization.Loc;
import main.model.claim.property.PropertyClaim;
import main.model.insurance.Insurance;
import main.model.insurance.InsuranceBuilder;

import java.io.Serializable;

/**
 * Property.java
 */
abstract class Property extends Insurance<PropertyClaim>
        implements Serializable
{

    private String postalCode;
    private String city;
    private int year;
    private Material material;
    private int squareMeter;
    private Standard standard;
    private String streetAddress;
    private int contents;

    public enum Material {
        A(Loc.c("property_material_a")),
        B(Loc.c("property_material_b")),
        C(Loc.c("property_material_c"));

        String value;

        Material(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    public enum Standard {
        A(Loc.c("property_standard_a")),
        B(Loc.c("property_standard_b")),
        C(Loc.c("property_standard_c"));

        String value;

        Standard(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    /**
     * Constructor - pushes builder object up the inheritance chain.
     * @param ib
     */
    public Property(InsuranceBuilder ib) { super(ib); }

    /* SETTERS */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public void setContents(int contents){ this.contents = contents; }

    /* GETTERS */
    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public int getContents(){ return contents; }

    public int getSquareMeter() {
        return squareMeter;
    }

    public Material getMaterial() {
        return material;
    }

    public Standard getStandard() {
        return standard;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    /* OVERRIDES */
    @Override
    public boolean query(String value)
    {
        return super.query(value)
                || (material != null && material.getValue().toLowerCase().contains(value.toLowerCase()))
                || (standard != null && standard.getValue().toLowerCase().contains(value.toLowerCase()));
    }
}
