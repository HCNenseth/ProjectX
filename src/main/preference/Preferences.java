package main.preference;

/**
 * Super simple config singleton for persistent storage of config data.
 */
public enum Preferences
{
    inst;

    private java.util.prefs.Preferences prefs;
    private final String dirName = "prettyUselessInsuranceSystem";

    Preferences() { prefs = java.util.prefs.Preferences.userRoot().node(dirName); }

    public void add(String key, String value)
    {
        prefs.put(key, value);
    }

    public String get(String key)
    {
        return prefs.get(key, "");
    }

    public void delete(String key)
    {
        prefs.remove(key);
    }

    public boolean has(String key)
    {
        return !prefs.get(key, "").equals("");
    }
}
