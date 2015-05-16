package main.model;

import main.localization.Loc;

/**
 * Model.java
 */
public interface Model
{
    /**
     * The enum Model type.
     */
    enum ModelType
    {
        /**
         * The PERSON.
         */
        PERSON(Loc.c("person")),
        /**
         * The INSURANCE.
         */
        INSURANCE(Loc.c("insurance")),
        /**
         * The CLAIM.
         */
        CLAIM(Loc.c("claim")),
        /**
         * The SEARCH.
         */
        SEARCH(Loc.c("search"));

        /**
         * The Value.
         */
        String value;

        /**
         * Instantiates a new Model type.
         *
         * @param value the value
         */
        ModelType(String value)
        {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return getValue();
        }
    }

    /**
     * Gets model type.
     *
     * @return the model type
     */
    ModelType getModelType();
}
