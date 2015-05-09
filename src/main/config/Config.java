package main.config;

import javafx.stage.Screen;

/**
 * Created by HansPetter on 29.04.2015.
 */
public class Config {

    public static final String APP_NAME = "Wakizashi";

    public static final String PERSONS = "persons";
    public static final String INSURANCES = "insurances";
    public static final String CLAIMS = "claims";

    public static final String CSS = "file:resources/css/";
    public static final String IMAGES = "file:resources/images/";

    public static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
    public static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() * 0.9;
    public static final int PD_HEIGHT = 300, PD_WIDTH = 400;

    public static final int STANDARD_YEAR = 1970;
    public static final int STANDARD_MONTH = 1;
    public static final int STANDARD_DAY = 1;

    public final static int TOTAL_CUSTOMER_INSURANCE_COUNT = 3;
    public final static int TOTAL_CUSTOMER_DISCOUNT = 10;


}
