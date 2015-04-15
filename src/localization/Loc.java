package localization;

/**
 * Created by alex on 4/15/15.
 */
public class Loc
{
    private Parser p;
    private String activeLang = "english";
    private static final Loc INSTANCE = new Loc();

    private Loc()
    {
        p = new Parser();
    }

    private String getValue(String key)
    {
        return p.get(activeLang, key);
    }

    public static String get(String key)
    {
        return Loc.INSTANCE.getValue(key);
    }

    public static void setActiveLang(String lang)
    {
        if (! Loc.INSTANCE.p.getLanguages().contains(lang)) {
            throw new IllegalStateException("No such language!");
        }
        Loc.INSTANCE.activeLang = lang;
    }
}
