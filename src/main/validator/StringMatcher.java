package main.validator;

/**
 * StringMatcher.java
 */
public enum StringMatcher
{
    /**
     * The INSTANCE.
     */
    INSTANCE;

    /**
     * Some simple rules. Edit here!
     */
    private final static String baseString = "[\\w\\s\\-æøåØÆÅ@].{2,20}";
    private final static String baseFloat = "[0-9]+([\\\\,\\\\.][0-9]+)?";
    private final static String baseDigit = "\\d+";
    private final static String liberation = ".*";

    private final static String email = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.\\w{2,4}";
    private final static String firstname = baseString;
    private final static String lastname = baseString;
    private final static String color = baseString;

    private final static String regnr = "\\w{2,8}";
    private final static String type = "\\w{2,20}";
    private final static String year = "\\d{4}";
    private final static String length = baseFloat;
    private final static String power = baseFloat;

    /**
     * Email boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean email(String val)
    {
        return val.matches(email);
    }

    /**
     * Firstname boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean firstname(String val)
    {
        return val.matches(firstname);
    }

    /**
     * Lastname boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean lastname(String val)
    {
        return val.matches(lastname);
    }

    /**
     * Regnr boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean regnr(String val)
    {
        return val.matches(regnr);
    }

    /**
     * Type boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean type(String val)
    {
        return val.matches(type);
    }

    /**
     * Color boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean color(String val)
    {
        return val.matches(color);
    }

    /**
     * Year boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean year(String val)
    {
        return val.matches(year);
    }

    /**
     * Length boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean length(String val)
    {
        return val.matches(length);
    }

    /**
     * Power boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean power(String val)
    {
        return val.matches(power);
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public static String getEmail()
    {
        return email;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public static String getYear()
    {
        return year;
    }

    /**
     * Gets digit.
     *
     * @return the digit
     */
    public static String getDigit()
    {
        return baseDigit;
    }

    /**
     * Gets float.
     *
     * @return the float
     */
    public static String getFloat()
    {
        return baseFloat;
    }

    /**
     * Gets base string.
     *
     * @return the base string
     */
    public static String getBaseString()
    {
        return baseString;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public static String getFirstname()
    {
        return firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public static String getLastname()
    {
        return lastname;
    }

    /**
     * Gets regnr.
     *
     * @return the regnr
     */
    public static String getRegnr()
    {
        return regnr;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public static String getPostalCode()
    {
        return year;
    }

    /**
     * Gets liberation.
     *
     * @return the liberation
     */
    public static String getLiberation()
    {
        return liberation;
    }
}
