package main.model;

import main.config.Config;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
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
        resetData();
    }

    /**
     * reset hashmap with empty lists.
     */
    public void resetData()
    {
        data.put(Config.PERSONS, new LinkedList<>());
        data.put(Config.INSURANCES, new LinkedList<>());
        data.put(Config.CLAIMS, new LinkedList<>());
    }

    /**
     * Static method for access from the outside.
     *
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
     *
     * @param f the f
     */
    public static void injectFilename(String f)
    {
        filename = f;
    }

    /**
     * Return isset boolean value for filename.
     * @return the boolean
     */
    public static boolean hasFilename()
    {
        return !(filename == null || filename.equals(""));
    }

    /**
     * Returns current filename
     *
     * @return filename
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * Read data from file.
     *
     * @throws IOException the iO exception
     * @throws IOException the class not found exception
     */
    public void read() throws IOException, ClassNotFoundException
    {
        if (filename == null)
            throw new IllegalStateException("Filename not set!");

        ObjectInputStream handle = new ObjectInputStream(
                new FileInputStream(filename));

        data = (Map) handle.readObject();

        handle.close();
    }

    /**
     * Save data to file.
     *
     * @throws IOException the iO exception
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
     *
     * @param key the key
     * @param list the list
     */
    public void put(String key, List<?> list)
    {
        data.put(key, list);
    }

    /**
     * Return List from data set.
     *
     * @param key the key
     * @return list
     */
    public List<?> get(String key)
    {
        return data.get(key);
    }
}