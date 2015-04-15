package model;

import model.insurance.Insurance;

import java.util.List;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Person
{
    private String firstname;
    private String lastname;
    private String streetAddress;
    private String city;
    private short postalCode;
    private List<Insurance> insurances;
    private List<Claim> claims;

    public Person(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
