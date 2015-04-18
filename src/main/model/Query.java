package main.model;

import java.util.Iterator;
import java.util.List;

/**
 * Created by alex on 4/17/15.
 *
 * Playing around with some query thoughts...
 */
public enum Query
{
    INSTANCE;

    /**
     * This is just a override for the method with status argument.
     *
     * Example usage:
     *
     *      while (Query.filter(persons, "Bob").hasNext())
     *
     * @param data - List input
     * @param q - query string
     * @return
     */
    public static Iterator<? extends FullTextSearch> filter(List<? extends FullTextSearch> data,
                                                  String q)
    {
        return filter(data, q, Status.ACTIVE);
    }

    /**
     * Filter function. Returns an iterator instance with filtered data.
     *
     * Example usage:
     *
     *      while (Query.filter(persons, "Bob", Status.INACTIVE).hasNext())
     *
     * @param data - List input
     * @param q - query string
     * @param s - status enum type
     * @return
     */
    public static Iterator<? extends FullTextSearch> filter(List<? extends FullTextSearch> data,
                                                 String q,
                                                 Status s)
    {
        return data.stream()
                .filter(i -> i.query(q) && i.getStatus().equals(s))
                .iterator();
    }
}
