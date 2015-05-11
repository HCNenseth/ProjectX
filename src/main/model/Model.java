package main.model;

/**
 * Model.java
 */
public interface Model
{
    enum ModelType { PERSON, INSURANCE, CLAIM, SEARCH }

    ModelType getModelType();
}
