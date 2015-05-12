package main.config;

import javafx.stage.Screen;

/**
 * Config.java;
 */
public class Config
{
    public static final String APP_NAME = "General Average";

    public static final String PERSONS = "persons";
    public static final String INSURANCES = "insurances";
    public static final String CLAIMS = "claims";

    public static final String CSS = "file:resources/css/";
    public static final String IMAGES = "file:resources/images/";
    public static final String UPLOADS = "uploads/";

    public static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
    public static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() * 0.9;
    public static final int PD_HEIGHT = 400, PD_WIDTH = 500;

    public static final int STANDARD_YEAR = 1970;
    public static final int STANDARD_MONTH = 1;
    public static final int STANDARD_DAY = 1;

    public final static int TOTAL_CUSTOMER_INSURANCE_COUNT = 3;
    public final static int TOTAL_CUSTOMER_DISCOUNT = 10;

    public final static int PERSON_COUNTER_START = 1000000;
    public final static int INSURANCE_COUNTER_START = 2000000;
    public final static int CLAIM_COUNTER_START = 3000000;

    public final static int MAX_UPLOAD_FILESIZE = 4096;
}
