package main.model;

/**
 * Created by alex on 4/26/15.
 */
public interface Model
{
    enum ModelType { PERSON, INSURANCE, CLAIM }

    ModelType getModelType();
}
