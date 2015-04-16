package model.insurance.vehicle;

import localization.Loc;
import model.Person;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Boat extends Vehicle
{
    private int registrationYear;
    private short length;
    private short horsePower;

    private Propulsion propulsion;
    private Type type;

    private enum Propulsion {
        A(Loc.get("boat_propulsion_a")),
        B(Loc.get("boat_propulsion_b")),
        C(Loc.get("boat_propulsion_c"));

        String value;

        Propulsion(String value)
        {
            this.value = value;
        }

        public String getValue() { return value; }
    }

    private enum Type {
        A(Loc.get("boat_type_a")),
        B(Loc.get("boat_type_b")),
        C(Loc.get("boat_type_c")),
        D(Loc.get("boat_type_d"));

        String value;

        Type(String value)
        {
            this.value = value;
        }

        public String getValue() { return value; }
    }

    public Boat(Person customer, double premium, double amount, Calendar date, String desc)
    {
        super(customer, premium, amount, date, desc);
    }

    public String getType()
    {
        return type.getValue();
    }

    public String getPropulsion()
    {
        return propulsion.getValue();
    }
}

