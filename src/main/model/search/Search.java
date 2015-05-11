package main.model.search;

import main.model.Model;

import java.time.LocalDate;

/**
 * Search.java
 */
public class Search implements Model
{
    private String keyword;
    private LocalDate from;
    private LocalDate to;

    public static class Builder
    {
        private String keyword;

        private LocalDate from = LocalDate.of(1930, 01, 01);
        private LocalDate to = LocalDate.now();

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

        public Search build() { return new Search(this); }
    }

    private Search(Builder builder)
    {
        this.keyword = builder.keyword;
        this.from = builder.from;
        this.to = builder.to;
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

    @Override
    public ModelType getModelType()
    {
        return ModelType.SEARCH;
    }
}
