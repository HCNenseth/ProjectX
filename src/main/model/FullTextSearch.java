package main.model;

import java.time.LocalDate;

/**
 * Created by alex on 4/17/15.
 */
public interface FullTextSearch
{
    boolean query(String value);
    LocalDate getDate();
    Status getStatus();
    default boolean between(LocalDate from, LocalDate to)
    {
        return (from.isBefore(getDate()) || from.isEqual(getDate()))
                && (to.isAfter(getDate()) || to.equals(getDate()));
    }
}
