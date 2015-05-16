package main.localization;

import java.util.Set;

/**
 * Simple main.localization system. This is the package config gateway
 * to the outside. Singleton pattern is used for preventing duplication
 * and some short and compact methods is defined.
 *
 * @date 2015 -04-15
 * @filename Loc.java
 */
public final class Loc
{
    private XMLParser p;
    private String activeLang = "english";
    private static final Loc INSTANCE = new Loc();

    /**
     * Prevent instantiation of this class
     */
    private Loc()
    {
        p = new XMLParser();
    }

    /**
     * Retrieve value from storage based on only key.
     * Language is set elsewhere.
     *
     * @param key - key to find in map
     * @return - value in map
     */
    private String getValue(final String key)
    {
        return p.get(activeLang, key);
    }

    private static String capitalize(final String string)
    {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    /**
     * Static method for usage outside package. This returns a capitalized string
     *
     * @param key - key to find in map
     * @return  - value in map
     */
    public static String c(final String key)
    {
        return capitalize(Loc.INSTANCE.getValue(key));
    }

    /**
     * Static method
     *
     * @param key - key to find in map
     * @return  - uppercase value in map
     */
    public static String u(final String key)
    {
        return Loc.INSTANCE.getValue(key).toUpperCase();
    }

    /**
     * Public static
     *
     * @param key - key to find in map
     * @return  - lowercase value in map
     */
    public static String l(final String key)
    {
        return Loc.INSTANCE.getValue(key).toLowerCase();
    }

    /**
     * Change lang. Throws  IllegalStateException if
     * the input arguments does not exists in the parsed
     * language files.
     *
     * @param lang the lang
     */
    public static void setActiveLang(String lang)
    {
        if (!Loc.INSTANCE.p.getLanguages().contains(lang)) {
            throw new IllegalStateException("No such language!");
        }
        Loc.INSTANCE.activeLang = lang;

    }

    /**
     * Return all available languages.
     *
     * @return languages
     */
    public static Set<String> getLanguages()
    {
        return Loc.INSTANCE.p.getLanguages();
    }

    /**
     * Return active language.
     *
     * @return active lang
     */
    public static String getActiveLang()
    {
        return Loc.INSTANCE.activeLang;
    }
}
