package main.config;

import javafx.stage.Screen;

/**
 * Config.java;
 */
public class Config
{
    /**
     * The constant APP_NAME.
     */
    public static final String APP_NAME = "General Average";
    /**
     * The constant VERSION.
     */
    public static final String VERSION = "1.01";

    /**
     * The constant PERSONS.
     */
    public static final String PERSONS = "persons";
    /**
     * The constant INSURANCES.
     */
    public static final String INSURANCES = "insurances";
    /**
     * The constant CLAIMS.
     */
    public static final String CLAIMS = "claims";

    /**
     * The constant CSS.
     */
    public static final String CSS = "file:resources/css/";
    /**
     * The constant IMAGES.
     */
    public static final String IMAGES = "file:resources/images/";
    /**
     * The constant UPLOADS.
     */
    public static final String UPLOADS = "uploads/";
    /**
     * The constant DATA.
     */
    public static final String DATA = "data/";

    /**
     * The constant PERSON_TAB_IMAGE.
     */
    public static final String PERSON_TAB_IMAGE = "user.png";
    /**
     * The constant CAR_TAB_IMAGE.
     */
    public static final String CAR_TAB_IMAGE = "car.png";
    /**
     * The constant BOAT_TAB_IMAGE.
     */
    public static final String BOAT_TAB_IMAGE = "anchor.png";
    /**
     * The constant HOUSE_TAB_IMAGE.
     */
    public static final String HOUSE_TAB_IMAGE = "house.png";
    /**
     * The constant TRAVEL_TAB_IMAGE.
     */
    public static final String TRAVEL_TAB_IMAGE = "world.png";
    /**
     * The constant CLAIM_TAB_IMAGE.
     */
    public static final String CLAIM_TAB_IMAGE = "package.png";
    /**
     * The constant SEARCH_TAB_IMAGE.
     */
    public static final String SEARCH_TAB_IMAGE = "magnifier.png";
    /**
     * The constant STATS_TAB_IMAGE.
     */
    public static final String STATS_TAB_IMAGE = "chart_pie.png";
    /**
     * The constant REPORT_TAB_IMAGE.
     */
    public static final String REPORT_TAB_IMAGE = "information.png";
    /**
     * The constant GENERIC_TAB_IMAGE.
     */
    public static final String GENERIC_TAB_IMAGE = "tab.png";

    /**
     * The constant HEIGHT.
     */
    public static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
    /**
     * The constant WIDTH.
     */
    public static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth() * 0.9;
    /**
     * The constant PD_HEIGHT.
     */
    public static final int PD_HEIGHT = 400, /**
 * The PD _ wIDTH.
 */
PD_WIDTH = 500;

    /**
     * The constant STANDARD_YEAR.
     */
    public static final int STANDARD_YEAR = 1970;
    /**
     * The constant STANDARD_MONTH.
     */
    public static final int STANDARD_MONTH = 1;
    /**
     * The constant STANDARD_DAY.
     */
    public static final int STANDARD_DAY = 1;

    /**
     * The constant TOTAL_CUSTOMER_INSURANCE_COUNT.
     */
    public final static int TOTAL_CUSTOMER_INSURANCE_COUNT = 3;
    /**
     * The constant TOTAL_CUSTOMER_DISCOUNT.
     */
    public final static int TOTAL_CUSTOMER_DISCOUNT = 10;

    /**
     * The constant PERSON_COUNTER_START.
     */
    public final static int PERSON_COUNTER_START = 1000000;
    /**
     * The constant INSURANCE_COUNTER_START.
     */
    public final static int INSURANCE_COUNTER_START = 2000000;
    /**
     * The constant CLAIM_COUNTER_START.
     */
    public final static int CLAIM_COUNTER_START = 3000000;

    /**
     * The constant MAX_UPLOAD_FILESIZE.
     */
    public final static int MAX_UPLOAD_FILESIZE = 4096;
}
