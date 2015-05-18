package main.config;

import javafx.stage.Screen;

public class Config
{
    public static final String APP_NAME = "General Average";
    public static final String VERSION = "1.01";

    public static final String PERSONS = "persons";
    public static final String INSURANCES = "insurances";
    public static final String CLAIMS = "claims";

    public static final String CSS = "file:resources/css/";
    public static final String IMAGES = "file:resources/images/";
    public static final String UPLOADS = "uploads/";
    public static final String DATA = "data/";

    public static final String PERSON_TAB_IMAGE = "user.png";
    public static final String CAR_TAB_IMAGE = "car.png";
    public static final String BOAT_TAB_IMAGE = "anchor.png";
    public static final String HOUSE_TAB_IMAGE = "house.png";
    public static final String TRAVEL_TAB_IMAGE = "world.png";
    public static final String CLAIM_TAB_IMAGE = "package.png";
    public static final String SEARCH_TAB_IMAGE = "magnifier.png";
    public static final String STATS_TAB_IMAGE = "chart_pie.png";
    public static final String REPORT_TAB_IMAGE = "information.png";
    public static final String GENERIC_TAB_IMAGE = "tab.png";

    public static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
    public static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() * 0.9;
    public static final int PD_HEIGHT = 400;
    public static final int PD_WIDTH = 500;

    public static final int STANDARD_YEAR = 1970;
    public static final int STANDARD_MONTH = 1;
    public static final int STANDARD_DAY = 1;

    public static final int TOTAL_CUSTOMER_INSURANCE_COUNT = 3;
    public static final int TOTAL_CUSTOMER_DISCOUNT = 10;

    public static final int PERSON_COUNTER_START = 1000000;
    public static final int INSURANCE_COUNTER_START = 2000000;
    public static final int CLAIM_COUNTER_START = 3000000;

    public static final int MAX_UPLOAD_FILESIZE = 4096;
}
