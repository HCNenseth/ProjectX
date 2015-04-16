package model.insurance.property;

import localization.Loc;
import model.Person;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class House extends Property
{
    private String streetAddress;
    private short postalCode;
    private String city;
    private int year;
    private int squareMeter;

    public enum Type {
        A(Loc.get("house_type_a")),
        B(Loc.get("house_type_b")),
        C(Loc.get("house_type_c"));

        String value;

        Type(String value) { this.value = value; }

        public String getValue() { return value; }

    }
    public enum Material {
        A(Loc.get("house_material_a")),
        B(Loc.get("house_material_b")),
        C(Loc.get("house_material_c"));

        String value;

        Material(String value) { this.value = value; }

        public String getValue() { return value; }
    }
    public enum Standard {
        A(Loc.get("house_standard_a")),
        B(Loc.get("house_standard_b")),
        C(Loc.get("house_standard_c"));

        String value;

        Standard(String value) { this.value = value; }

        public String getValue() { return value; }
    }

    public House(Person customer, double premium, double amount, Calendar date, String desc)
    {
        super(customer, premium, amount, date, desc);
    }
}
