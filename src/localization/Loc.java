package localization;

/**
 * Created by alex on 4/15/15.
 */
public class Loc
{
    private Parser p;
    private String activeLang = "english";

    public Loc()
    {
        p = new Parser();

        for (String s : p.getLanguages())
        {
            activeLang = s;
            break;
        }
    }

    public String get(String key)
    {
        return p.get(activeLang, key);
    }

    public void setActiveLang(String lang)
    {
        if (! p.getLanguages().contains(lang)) {
            throw new IllegalStateException("No such language!");
        }
        activeLang = lang;
    }
}
