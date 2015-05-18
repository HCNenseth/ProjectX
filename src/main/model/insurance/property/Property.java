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
    private String streetAddress;
    private String postalCode;
    private String city;
    private Material material;
    private Standard standard;
    private int year;
    private int squareMeter;
    private double contents;

    public enum Material
    {
        A(Loc.c("property_material_a")),
        B(Loc.c("property_material_b")),
        C(Loc.c("property_material_c"));

        String value;

        /**
         * Instantiates a new Material.
         *
         * @param value the value
         */
        Material(String value)
        {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return getValue();
        }
    }

    public enum Standard
    {
        A(Loc.c("property_standard_a")),
        B(Loc.c("property_standard_b")),
        C(Loc.c("property_standard_c"));

        String value;

        /**
         * Instantiates a new Standard.
         *
         * @param value the value
         */
        Standard(String value)
        {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return getValue();
        }
    }

    /**
     * Constructor - pushes builder object up the inheritance chain.
     *
     * @param ib the ib
     */
    protected Property(InsuranceBuilder ib)
    {
        super(ib);
    }

    /***********
     * SETTERS *
     ***********/

    /**
     * Sets street address.
     *
     * @param streetAddress the street address
     */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * Sets material.
     *
     * @param material the material
     */
    public void setMaterial(Material material)
    {
        this.material = material;
    }

    /**
     * Sets square meter.
     *
     * @param squareMeter the square meter
     */
    public void setSquareMeter(int squareMeter)
    {
        this.squareMeter = squareMeter;
    }

    /**
     * Sets standard.
     *
     * @param standard the standard
     */
    public void setStandard(Standard standard)
    {
        this.standard = standard;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     */
    public void setContents(double contents)
    {
        this.contents = contents;
    }

    /***********
     * GETTERS *
     ***********/

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Gets contents.
     *
     * @return the contents
     */
    public double getContents()
    {
        return contents;
    }

    /**
     * Gets square meter.
     *
     * @return the square meter
     */
    public int getSquareMeter()
    {
        return squareMeter;
    }

    /**
     * Gets material.
     *
     * @return the material
     */
    public Material getMaterial()
    {
        return material;
    }

    /**
     * Gets standard.
     *
     * @return the standard
     */
    public Standard getStandard()
    {
        return standard;
    }

    /**
     * Gets street address.
     *
     * @return the street address
     */
    public String getStreetAddress()
    {
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
