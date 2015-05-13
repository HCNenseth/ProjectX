package main.model;

import main.localization.Loc;

/**
 * Model.java
 */
public interface Model
{
    enum ModelType
    {
        PERSON(Loc.c("person")),
        INSURANCE(Loc.c("insurance")),
        CLAIM(Loc.c("claim")),
        SEARCH(Loc.c("search"));

        String value;

        ModelType(String value) { this.value = value; }

        public String getValue() { return value; }

        @Override
        public String toString() { return getValue(); }
    }

    ModelType getModelType();
}
