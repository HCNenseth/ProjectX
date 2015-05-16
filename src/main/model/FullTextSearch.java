package main.model;

import java.time.LocalDate;

/**
 * FullTextSearch.java
 */
public interface FullTextSearch
{
    /**
     * Query boolean.
     *
     * @param value the value
     * @return the boolean
     */
    boolean query(String value);

    /**
     * Gets date.
     *
     * @return the date
     */
    LocalDate getDate();

    /**
     * Gets edited.
     *
     * @return the edited
     */
    LocalDate getEdited();

    /**
     * Gets status.
     *
     * @return the status
     */
    Status getStatus();

    /**
     * Between boolean.
     *
     * @param from the from
     * @param to the to
     * @return the boolean
     */
    default boolean between(LocalDate from, LocalDate to)
    {
        return (from.isBefore(getDate()) || from.isEqual(getDate()))
                && (to.isAfter(getDate()) || to.equals(getDate()));
    }
}
