package main.preference;

import java.util.prefs.Preferences;

/**
 * Super simple config singleton for persistent storage of config data.
 */
public enum Config
{
    inst;

    private Preferences prefs;
    private final String dirName = "prettyUselessInsuranceSystem";

    Config() { prefs = Preferences.userRoot().node(dirName); }

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
