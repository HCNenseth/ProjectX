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

    public static class Builder
    {
        private String keyword;

        private LocalDate from = LocalDate.of(1930, 01, 01);
        private LocalDate to = LocalDate.now();
        private Set<Status> status = new HashSet<>();
        private Set<ModelType> models = new HashSet<>();

        public Builder(String keyword)
        {
            this.keyword = keyword;
        }

        public Builder from(LocalDate from)
        {
            this.from = from;
            return this;
        }

        public Builder to(LocalDate to)
        {
            this.to = to;
            return this;
        }

        public Builder status(Set<Status> status)
        {
            this.status = status;
            return this;
        }

        public Builder models(Set<ModelType> models)
        {
            this.models = models;
            return this;
        }

        public Search build() { return new Search(this); }
    }

    private Search(Builder builder)
    {
        this.keyword = builder.keyword;
        this.from = builder.from;
        this.to = builder.to;
        this.status = builder.status;
        this.models = builder.models;
    }

    /* GETTERS */
    public String getKeyword()
    {
        return keyword;
    }

    public LocalDate getFrom()
    {
        return from;
    }

    public LocalDate getTo()
    {
        return to;
    }

    public Set<Status> getStatus()
    {
        return status;
    }

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
