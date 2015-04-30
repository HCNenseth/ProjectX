package main.preference;

/**
 * Created by alex on 4/30/15.
 */
public enum Pref
{
    inst;

    private XMLParser p;

    Pref() { p = new XMLParser(); }

    public String get(String key)
    {
        return p.get(key);
    }

    public boolean has(String key)
    {
        return p.get(key) != null;
    }

    public void put(String key, String value)
    {
        p.put(key, value);
    }

}
