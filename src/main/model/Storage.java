package main.model;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Storage class. This class handles all file read and write operations.
 */
public class Storage
{
    private static String filename;
    private static final Storage INSTANCE = new Storage();

    private Map<String, List<?>> data;

    /**
     * Singleton pattern
     */
    private Storage()
    {
        data = new HashMap<>();
    }

    /**
     * Static method for access from the outside.
     * @return single instance
     */
    public static Storage getInstance()
    {
        if (filename == null)
            throw new IllegalStateException("Filename not set!");

        return INSTANCE;
    }

    /**
     * Set filename to read for reading and writing data to.
     * @param f
     */
    public static void injectFilename(String f)
    {
        filename = f;
    }

    /**
     * Return isset boolean value for filename.
     */
    public static boolean hasFilename()
    {
        return !(filename == null || filename.equals(""));
    }

    /**
     * Read data from file.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void read() throws IOException, ClassNotFoundException
    {
        if (filename == null)
            throw new IllegalStateException("Filename not set!");

        ObjectInputStream handle = new ObjectInputStream(
                new FileInputStream(filename));

        data = (Map)handle.readObject();
    }

    /**
     * Save data to file.
     * @throws IOException
     */
    public void save() throws IOException
    {
        if (filename == null)
            throw new IllegalStateException("Filename not set!");

        ObjectOutputStream handle = new ObjectOutputStream(
                new FileOutputStream(filename));

        handle.writeObject(data);
        handle.close();
    }

    /**
     * Put List into data set, accept any List with any object type.
     * @param key
     * @param list
     */
    public void put(String key, List<?> list)
    {
        data.put(key, list);
    }

    /**
     * Return List from data set.
     * @param key
     * @return
     */
    public List<?> get(String key)
    {
        return data.get(key);
    }
}