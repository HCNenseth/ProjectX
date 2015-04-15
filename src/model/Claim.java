package model;

import model.insurance.Insurance;

import java.util.Calendar;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Claim
{
    private String desc;
    private String contacts;
    private float amount;
    private Calendar date;
    private Person customer;
    private Insurance insurance;
    private enum type {A, B, C}

    public float payout()
    {
        return 0;
        //return amount - insurance.getDeductable();
    }
}
