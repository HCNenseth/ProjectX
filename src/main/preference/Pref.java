package main.preference;

/**
 * Created by alex on 4/30/15.
 */
public enum Pref
{
    inst;

    private XMLParser p;

    /**
     * Instantiates a new Pref.
     */
    Pref()
    {
        p = new XMLParser();
    }

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    public String get(String key)
    {
        return p.get(key);
    }

    /**
     * Has boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean has(String key)
    {
        return p.get(key) != null;
    }

    /**
     * Put void.
     *
     * @param key the key
     * @param value the value
     */
    public void put(String key, String value)
    {
        p.put(key, value);
    }

}
