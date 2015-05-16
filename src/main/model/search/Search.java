package main.model.search;

import main.model.Model;
import main.model.Status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Search.java
 */
public class Search implements Model
{
    private String keyword;
    private LocalDate from;
    private LocalDate to;
    private Set<Status> status;
    private Set<ModelType> models;

    /**
     * The type Builder.
     */
    public static class Builder
    {
        private String keyword;

        private LocalDate from = LocalDate.of(1930, 01, 01);
        private LocalDate to = LocalDate.now();
        private Set<Status> status = new HashSet<>();
        private Set<ModelType> models = new HashSet<>();

        /**
         * Instantiates a new Builder.
         *
         * @param keyword the keyword
         */
        public Builder(String keyword)
        {
            this.keyword = keyword;
        }

        /**
         * From builder.
         *
         * @param from the from
         * @return the builder
         */
        public Builder from(LocalDate from)
        {
            this.from = from;
            return this;
        }

        /**
         * To builder.
         *
         * @param to the to
         * @return the builder
         */
        public Builder to(LocalDate to)
        {
            this.to = to;
            return this;
        }

        /**
         * Status builder.
         *
         * @param status the status
         * @return the builder
         */
        public Builder status(Set<Status> status)
        {
            this.status = status;
            return this;
        }

        /**
         * Models builder.
         *
         * @param models the models
         * @return the builder
         */
        public Builder models(Set<ModelType> models)
        {
            this.models = models;
            return this;
        }

        /**
         * Build search.
         *
         * @return the search
         */
        public Search build()
        {
            return new Search(this);
        }
    }

    private Search(Builder builder)
    {
        this.keyword = builder.keyword;
        this.from = builder.from;
        this.to = builder.to;
        this.status = builder.status;
        this.models = builder.models;
    }

    /**
     * Gets keyword.
     *
     * @return the keyword
     */
/* GETTERS */
    public String getKeyword()
    {
        return keyword;
    }

    /**
     * Gets from.
     *
     * @return the from
     */
    public LocalDate getFrom()
    {
        return from;
    }

    /**
     * Gets to.
     *
     * @return the to
     */
    public LocalDate getTo()
    {
        return to;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Set<Status> getStatus()
    {
        return status;
    }

    /**
     * Gets models.
     *
     * @return the models
     */
    public Set<ModelType> getModels()
    {
        return models;
    }

    @Override
    public ModelType getModelType()
    {
        return ModelType.SEARCH;
    }
}
