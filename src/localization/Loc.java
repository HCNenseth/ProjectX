package localization;

/**
 * Simple localization system. This is the package static gateway
 * to the outside. Singleton pattern is used for preventing duplication
 * and some short and compact methods is defined.
 *
 * @date 2015-04-15
 * @filename Loc.java
 */
public final class Loc
{
    private XMLParser p;
    private String activeLang = "english";
    private static final Loc INSTANCE = new Loc();

    /**
     * Prevent instanciation of this class
     */
    private Loc()
    {
        p = new XMLParser();
    }

    /**
     * Retrieve value from storage based on only key.
     * Language is set elsewhere.
     * @param key
     * @return
     */
    private String getValue(final String key)
    {
        return p.get(activeLang, key);
    }

    /**
     * Static method for usage outside package.
     * @param key
     * @return
     */
    public static String get(final String key)
    {
        return Loc.INSTANCE.getValue(key);
    }

    /**
     * Change lang. Throws  IllegalStateException if
     * the input arguments does not exists in the parsed
     * language files.
     * @param lang
     */
    public static void setActiveLang(String lang)
    {
        if (!Loc.INSTANCE.p.getLanguages().contains(lang)) {
            throw new IllegalStateException("No such language!");
        }
        Loc.INSTANCE.activeLang = lang;
    }
}
