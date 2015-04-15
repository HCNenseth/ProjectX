package model;

/**
 * Created by HansChristian on 15.04.2015.
 */
public class Storage
{
    private static String filename;
    private static final Storage INSTANCE = new Storage();

    private Storage()
    {
        //
    }

    public static Storage getInstance()
    {
        if (filename == null)
            throw new IllegalStateException("Filname not set!");

        return INSTANCE;
    }

    public static void injectFilename(String f)
    {
        filename = f;
    }

    public void read()
    {
        if (filename == null)
            throw new IllegalStateException("Filename not set!");

        // TODO implement read from file
    }

    public void save()
    {
        if (filename == null)
            throw new IllegalStateException("Filename not set!");

        // TODO implement save to file
    }
}